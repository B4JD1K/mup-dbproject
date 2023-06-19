package com.example.application.views.register;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterForm {

    @NotEmpty(message = "Username is required")
    @Size(max = 50, message = "Username must be up to 50 characters")
    private String username;

    @NotEmpty(message = "Email is required")
    @Size(max = 100, message = "Email must be up to 100 characters")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(max = 100, message = "Password must be up to 100 characters")
    private String password;

    // Add getters and setters for the fields

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
