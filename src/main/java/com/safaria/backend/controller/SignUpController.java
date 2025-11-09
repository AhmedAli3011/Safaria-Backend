package com.safaria.backend.controller;

import com.safaria.backend.service.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.safaria.backend.DTO.TourProviderRequestDTO;
import com.safaria.backend.DTO.TouristSignUpDTO;

@CrossOrigin(origins = "http://localhost:8080") // Allow requests from Angular frontend
@RestController
@RequestMapping("/api/auth/signup")
@Validated
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/tourists")
    public ResponseEntity<String> touristSignUp(@Valid @RequestBody TouristSignUpDTO data) {
        signUpService.touristSignUp(data);
        return ResponseEntity.ok("Tourist registered successfully");
    }

    @PostMapping(value = "/tour-providers", consumes = "multipart/form-data")
     public ResponseEntity<String> tourProviderApplication(@Valid @ModelAttribute TourProviderRequestDTO data) {
        signUpService.tourProviderSignUp(data);
        return ResponseEntity.ok("Tour Provider application submitted successfully");
     }

}
