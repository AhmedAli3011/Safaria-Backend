package com.safaria.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDTO {
    @Email(message = "Email should be valid")
    @NotNull
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotNull
    private String password;
}
