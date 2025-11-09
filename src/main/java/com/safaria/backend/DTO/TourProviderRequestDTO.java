package com.safaria.backend.DTO;

import lombok.Data;

import java.time.Instant;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class TourProviderRequestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotBlank(message = "phone is mandatory")
    private String phone;
    @NotBlank(message = "country is mandatory")
    private String country;
    @NotBlank(message = "BusinessLicense is mandatory")
    private MultipartFile businessLicense;
    private String description;

}
