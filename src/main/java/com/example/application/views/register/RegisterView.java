package com.example.application.views.register;

import com.example.application.security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@AnonymousAllowed
@PageTitle("register")
@Route(value = "register")
public class RegisterView extends VerticalLayout implements BeforeEnterObserver {

    private final AuthenticatedUser authenticatedUser;
    private final Binder<RegisterForm> binder;

    public RegisterView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        binder = new BeanValidationBinder<>(RegisterForm.class);

        TextField firstNameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        TextField usernameField = new TextField("Username");
        EmailField emailField = new EmailField("Email");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");

        binder.bind(firstNameField, "firstName");
        binder.bind(lastNameField, "lastName");
        binder.bind(usernameField, "username");
        binder.bind(emailField, "email");
        binder.bind(passwordField, "password");

        Button registerButton = new Button("Register", event -> register());
        Button cancelButton = new Button("Cancel", event -> cancel());

        FormLayout formLayout = new FormLayout(firstNameField, lastNameField, usernameField, emailField, passwordField, confirmPasswordField);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        formLayout.setColspan(firstNameField, 2);
        formLayout.setColspan(lastNameField, 2);
        formLayout.setColspan(usernameField, 2);
        formLayout.setColspan(emailField, 2);
        formLayout.setColspan(passwordField, 2);
        formLayout.setColspan(confirmPasswordField, 2);

        HorizontalLayout buttonLayout = new HorizontalLayout(registerButton, cancelButton);
        buttonLayout.setSpacing(true);

        add(formLayout, buttonLayout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            // Already logged in
            event.forwardTo("");
        }
    }

    private void register() {
        RegisterForm registerForm = new RegisterForm();
        if (binder.writeBeanIfValid(registerForm)) {
            // Registration logic goes here
            Notification.show("Registration Successful!", 3000, Notification.Position.MIDDLE);
            UI.getCurrent().navigate("login");
        } else {
            binder.validate();
        }
    }

    private void cancel() {
        UI.getCurrent().navigate("start");
    }
}
