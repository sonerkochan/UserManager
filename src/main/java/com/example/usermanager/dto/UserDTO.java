package com.example.usermanager.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private LocalDate dateOfBirth;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;
}
