package com.example.application.views.myaccount;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Moje konto")
@Route(value = "account", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class MyAccountView extends Div {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");
    private EmailField confirmEmail = new EmailField("Confirm email address");
    private PasswordField password = new PasswordField("Password");
    private PasswordField confirmPassword = new PasswordField("Confirm password");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<User> binder = new Binder<>(User.class);
    private UserService userService;

    public MyAccountView(UserService userService) {
        this.userService = userService;
        addClassName("my-account-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            if (password.getValue().equals(confirmPassword.getValue())) {
                User user = binder.getBean();
                userService.updateUser(user);
                Notification.show(user.getClass().getSimpleName() + " zapisano zmiany.");
                clearForm();
            } else {
                Notification.show("Hasła lub adresy email nie są zgodne.");
            }
        });
    }

    private void clearForm() {
        binder.setBean(new User());
        password.clear();
        confirmPassword.clear();
    }

    private Component createTitle() {
        return new H3("Informacje o koncie");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Proszę podać poprawny adres email.");
        formLayout.add(firstName, lastName, email, confirmEmail, password, confirmPassword);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}

