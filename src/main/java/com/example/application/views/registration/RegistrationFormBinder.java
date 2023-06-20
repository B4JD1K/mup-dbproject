package com.example.application.views.registration;

import com.example.application.data.entity.User;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

public class RegistrationFormBinder {

    private RegistrationForm registrationForm;

    private boolean enablePasswordValidation;
    private boolean checkAcceptTerms;

    public RegistrationFormBinder(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    public void addBindingAndValidation() {
        BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(registrationForm);

        binder.forField(registrationForm.getPasswordField())
                .withValidator(this::passwordValidator).bind("password");

        binder.forField(registrationForm.getAcceptTermsCheckbox())
                .withValidator(this::acceptTermsValidator)
                .bind("acceptTerms");

        registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {
            enablePasswordValidation = true;

            binder.validate();
        });

        binder.setStatusLabel(registrationForm.getErrorMessageField());

        // do rejestracji
        registrationForm.getSubmitButton().addClickListener(event -> {
            try {
                User userBean = new User();

                binder.writeBean(userBean);

                showSuccess(userBean);
            } catch (ValidationException exception) {
                exception.printStackTrace();
            }
        });
    }

    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {
        // sprawdź czy hasło jest dłuższe niż 8 znaków
        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Hasło musi mieć co najmniej 8 znaków");
        }

        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        String pass2 = registrationForm.getPasswordConfirmField().getValue();

        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Hasła nie są takie same");
    }

    private ValidationResult acceptTermsValidator(Boolean acceptTerms, ValueContext ctx) {
        if (acceptTerms != null && acceptTerms) {
            return ValidationResult.ok();
        } else {
            Notification notification =
                    Notification.show("Musisz zaakceptować regulamin");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            return (ValidationResult) notification;
        }
    }

    private void showSuccess(User userBean) {
        Notification notification =
                Notification.show("Zapisano. Witaj " + userBean.getUsername());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

}
