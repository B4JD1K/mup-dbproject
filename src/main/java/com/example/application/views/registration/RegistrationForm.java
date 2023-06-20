package com.example.application.views.registration;

import com.example.application.data.entity.User;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;

public class RegistrationForm extends FormLayout {

    private H3 title;

    private TextField userName;

    private EmailField email;

    private PasswordField password;
    private PasswordField passwordConfirm;

    private Checkbox allowMarketing;
    private Checkbox acceptTerms;

    private Span errorMessageField;

    private Button submitButton;
    private Binder<User> binder = new Binder<>(User.class);
    private RegistrationSuccessListener registrationSuccessListener;


    public RegistrationForm() {
        title = new H3("Rejestracja");
        userName = new TextField("Nazwa użytkownika");
        email = new EmailField("Email");

        password = new PasswordField("Hasło");
        passwordConfirm = new PasswordField("Potwierdź hasło");

        allowMarketing = new Checkbox("Zgoda na otrzymywanie informacji handlowych");
        allowMarketing.getStyle().set("margin-top", "10px");

        acceptTerms = new Checkbox("Akceptuję regulamin.");
        acceptTerms.getStyle().set("margin-top", "10px");


        setRequiredIndicatorVisible(userName, email,
                password, passwordConfirm);

        errorMessageField = new Span();

        submitButton = new Button("Zarejestruj się");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(title, userName, email, password,
                passwordConfirm, acceptTerms, allowMarketing,
                errorMessageField, submitButton);

        setMaxWidth("500px");

        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        // These components always take full width
        setColspan(title, 2);
        setColspan(errorMessageField, 2);
        setColspan(acceptTerms, 2);
        setColspan(allowMarketing, 2);
        setColspan(submitButton, 2);


        submitButton.addClickListener(event -> {
            User user = new User();
            if (binder.writeBeanIfValid(user)) {
                saveUserToDatabase(user);

                if (registrationSuccessListener != null) {
                    registrationSuccessListener.onRegistrationSuccess(user);
                }
            } else {
                Notification.show("Wypełnij wymagane pola", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
    }
    private void saveUserToDatabase(User user) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/projekt");
        config.setUsername("odadoz");
        config.setPassword("123456");

        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            String query = "INSERT INTO users (username, email, name, password, hashed_password) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getName());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getHashedPassword());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    Notification.show("Zapisano użytkownika pomyślnie", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("Błąd podczas zapisywania użytkownika", 3000, Notification.Position.MIDDLE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

    public Checkbox getAcceptTermsCheckbox(){ return acceptTerms; }

    // pomyślne zarejestrowanie użytkownika
    public void addRegistrationSuccessListener(ComponentEventListener<RegistrationSuccessEvent> listener) {
        addListener(RegistrationSuccessEvent.class, listener);
    }

    // Definiuj interfejs RegistrationSuccessListener
    public interface RegistrationSuccessListener extends Serializable {
        void onRegistrationSuccess(User user);
    }

    // definiowanie pomyślnego zarejestrowania użytkownika
    public static class RegistrationSuccessEvent extends ComponentEvent<RegistrationForm> {
        private final User user;

        public RegistrationSuccessEvent(RegistrationForm source, User user) {
            super(source, false);
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }
}
