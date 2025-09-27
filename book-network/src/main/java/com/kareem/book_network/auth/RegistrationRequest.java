package com.kareem.book_network.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "first name is required")
    @NotBlank(message = "first name is required")
    private String firstName;

    @NotEmpty(message = "first name is required")
    @NotBlank(message = "last name is required")
    private String lastName;

    @Email(message = "Email isn't valid")
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email name is required")
    private String email;

    @Size(min = 8, message = "password must be at least 8 characters")
    @NotEmpty(message = "password is required")
    @NotBlank(message = "password is required")
    private String password;

}
