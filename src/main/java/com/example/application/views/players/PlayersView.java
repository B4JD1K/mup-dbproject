package com.example.application.views.players;

import com.example.application.data.entity.Players;
import com.example.application.data.service.PlayerService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.annotation.Secured;

import java.util.Optional;

@PageTitle("Zawodnicy")
@Route(value = "players/:action?(edit)", layout = MainLayout.class)
@AnonymousAllowed
public class PlayersView extends Div implements BeforeEnterObserver {

    private final String PLAYERS_ID = "playersID";
    private final String TEAM_EDIT_ROUTE_TEMPLATE = "players/%s/edit";

    private final Grid<Players> grid = new Grid<>(Players.class, false);

    private TextField imie;
    private TextField nazwisko;
    private TextField nick;
    private TextField pozycja;
    private TextField druzyna;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final BeanValidationBinder<Players> binder;

    private Players players;

    private final PlayerService playerService;

    @Autowired
    public PlayersView(PlayerService playerService) {
        this.playerService = playerService;
        addClassNames("players-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("imie").setAutoWidth(true);
        grid.addColumn("nazwisko").setAutoWidth(true);
        grid.addColumn("nick").setAutoWidth(true);
        grid.addColumn("pozycja").setAutoWidth(true);
        grid.addColumn("druzyna").setAutoWidth(true);
        grid.setItems(query -> playerService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // Configure Form
        binder = new BeanValidationBinder<>(Players.class);

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.players == null) {
                    this.players = new Players();
                }
                binder.writeBean(this.players);
                playerService.update(this.players);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(PlayersView.class);
            } catch (ValidationException validationException) {
                Notification.show("Błąd podczas aktualizacji danych, sprawdź poprawność wprowadzonych danych");
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> playerID = event.getRouteParameters().get(PLAYERS_ID).map(Long::parseLong);
        if (playerID.isPresent()) {
            Optional<Players> playersFromBackend = playerService.get(playerID.get());
            if (playersFromBackend.isPresent()) {
                populateForm(playersFromBackend.get());
            } else {
                Notification.show(String.format("", playerID.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(PlayersView.class);
            }
        }
    }
    @Secured("ROLE_ADMIN")
    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        imie = new TextField("Imie");
        nazwisko = new TextField("Nazwisko");
        nick = new TextField("Nick");
        pozycja = new TextField("Pozycja");
        druzyna = new TextField("Druzyna");
        formLayout.add(imie, nazwisko, nick, pozycja, druzyna);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Players value) {
        this.players = value;
        binder.readBean(this.players);
    }
}
