package com.pixisphere.dto.request;

import com.pixisphere.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Full Name Cannot be Empty")
    @Size(min = 3, message = "Full Name must be MIN 3 Character Long")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z]*$",
            message = "Name must start with a capital letter and contain only alphabets"
    )
    private String name;

    @NotBlank(message = "Email Cannot be Empty")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must have at least 6 characters, 1 uppercase letter, and 1 digit"
    )
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
    private String phone;

    @NotNull(message = "Role is mandatory")
    private Role role;
}
