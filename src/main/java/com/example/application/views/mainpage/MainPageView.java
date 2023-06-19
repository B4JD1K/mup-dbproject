package com.example.application.views.mainpage;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Main Page")
@Route(value = "start", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class MainPageView extends Composite<VerticalLayout> {

    public MainPageView() {
        getContent().setSizeFull();
        createSections();
    }
    private void createSections() {
        getContent().add(createSection("Strona Główna", "Witaj na stronie głównej naszego serwisu! Tutaj znajdziesz informacje szczegółnie skupiające się na grze CS:GO. Poznaj drużyny i zawodników oraz wiele więcej."));
        getContent().add(createSection("Zawodnicy", "Dowiedz się więcej o zawodnikach CS:GO. Zobacz ich narodowość i wiele innych szczegółów."));
        getContent().add(createSection("Drużyny", "Przeglądaj drużyny CS:GO i poznaj ich rankingi, nazwy, narodowości i skróty. Zobacz listę zawodników należących do danej drużyny."));
        getContent().add(createSection("Search", "Wyszukaj informacje na temat drużyny lub zawodników i ich pozycji przy pomocy listy zawodników należących do danej drużyny."));
    }

    private Section createSection(String headerText, String paragraphText) {
        H2 header = new H2(headerText);
        Paragraph paragraph = new Paragraph(paragraphText);
        return new Section(header, paragraph);
    }

    private static class Section extends Div {
        public Section(H2 heading, Paragraph paragraphs) {
            add(heading);
            add(paragraphs);
        }
    }
}