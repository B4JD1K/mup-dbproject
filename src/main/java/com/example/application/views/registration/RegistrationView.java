package com.example.application.views.registration;

import com.example.application.data.entity.User;
import com.example.application.views.mainpage.MainPageView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.example.application.views.MainLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// chce aby po zalogowaniu sie nie mozna bylo wyswietlic strony rejestracji
@AnonymousAllowed
@PageTitle("Rejestracja")
@Route(value = "registration", layout = MainLayout.class)
public class RegistrationView extends VerticalLayout {

    RegistrationForm registrationForm = new RegistrationForm();

    public RegistrationView() {
        addClassName("registration-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, registrationForm);

        add(registrationForm);

        RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registrationForm);
        registrationFormBinder.addBindingAndValidation();

        registrationForm.addRegistrationSuccessListener(event -> {
            User registeredUser = event.getUser();

            saveUserToDatabase(registeredUser);

            getUI().ifPresent(ui -> ui.navigate(MainPageView.class));

            Notification.show("Rejestracja pomyślna",
                            3000,
                            Notification.Position.MIDDLE)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });

        add(registrationForm);
    }

    private void saveUserToDatabase(User user) {

        String dburl = "jdbc:mysql://localhost:3306/projekt";
        String dbusername = "odadoz";
        String dbpassword = "123456";

        String query = "INSERT INTO users (username, email, name, password, hashed_password) VALUES (?, ?, ?, ?, ?)";


        try (Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getHashedPassword());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                Notification.show("Zapisano użytkownika pomyślnie",
                        3000,
                        Notification.Position.MIDDLE);
            } else {
                Notification.show("Błąd podczas zapisywania użytkownika",
                        3000,
                        Notification.Position.MIDDLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


//
//// VerticalLayout implements BeforeEnterObserver {
////
////    private final AuthenticatedUser authenticatedUser;
////    private final Binder<RegisterForm> binder;
////
////    public RegisterView(AuthenticatedUser authenticatedUser) {
////        this.authenticatedUser = authenticatedUser;
////        binder = new BeanValidationBinder<>(RegisterForm.class);
////
////        TextField firstNameField = new TextField("First Name");
////        TextField lastNameField = new TextField("Last Name");
////        TextField usernameField = new TextField("Username");
////        EmailField emailField = new EmailField("Confirm Email");
////        EmailField confirmEmailField = new EmailField("Email");
////        PasswordField passwordField = new PasswordField("Password");
////        PasswordField confirmPasswordField = new PasswordField("Confirm Password");
////
////        binder.bind(firstNameField, "firstName");
////        binder.bind(lastNameField, "lastName");
////        binder.bind(usernameField, "username");
////        binder.bind(emailField, "email");
////        binder.bind(passwordField, "password");
////
////        Button registerButton = new Button("Register", event -> register());
////        Button cancelButton = new Button("Cancel", event -> cancel());
////
////        FormLayout formLayout = new FormLayout(firstNameField, lastNameField, usernameField, emailField, passwordField, confirmPasswordField);
////        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
////
////        formLayout.setColspan(firstNameField, 2);
////        formLayout.setColspan(lastNameField, 2);
////        formLayout.setColspan(usernameField, 2);
////        formLayout.setColspan(emailField, 2);
////        formLayout.setColspan(confirmEmailField, 2);
////        formLayout.setColspan(passwordField, 2);
////        formLayout.setColspan(confirmPasswordField, 2);
////
////        HorizontalLayout buttonLayout = new HorizontalLayout(registerButton, cancelButton);
////        buttonLayout.setSpacing(true);
////
////        add(formLayout, buttonLayout);
////    }
////
////    @Override
////    public void beforeEnter(BeforeEnterEvent event) {
////        if (authenticatedUser.get().isPresent()) {
////            // Already logged in
////            event.forwardTo("");
////        }
////    }
////
////    private void register() {
////        RegisterForm registerForm = new RegisterForm();
////        if (binder.writeBeanIfValid(registerForm)) {
////            // Registration logic goes here
////            Notification.show("Registration Successful!", 3000, Notification.Position.MIDDLE);
////            UI.getCurrent().navigate("login");
////        } else {
////            binder.validate();
////        }
////    }
////
////    private void cancel() {
////        UI.getCurrent().navigate("start");
////    }
////}
