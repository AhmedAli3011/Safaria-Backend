package com.safaria.backend.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

import com.safaria.backend.entity.TourProvider;
import com.safaria.backend.entity.Tourist;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String country;
    private List<String> tourismTypes;
    private String aboutMe;
    private List<String> myTrips;
    private List<String> myClients;
    private List<String> myReviews;
    private float rating;
    private String type;
    private String token;

    public UserInfoDTO(Tourist tourist) {
        this.name = tourist.getUser().getName();
        this.email = tourist.getUser().getEmail();
        this.password = tourist.getUser().getPassword();
        this.country = tourist.getNationality();

        this.aboutMe = "I am A tourist.getUser()";
        this.myTrips = new ArrayList<>();
        this.myClients = new ArrayList<>();
        this.myReviews = new ArrayList<>();
        this.rating = 5;
    }

    public UserInfoDTO(TourProvider tourProvider) {
       this.name = tourProvider.getUser().getName();
        this.email = tourProvider.getUser().getEmail();
        this.password = tourProvider.getUser().getPassword();
        this.phoneNumber = tourProvider.getPhone();
        this.country = tourProvider.getCountry();
        

        this.aboutMe = "I am A TourProvider";
        this.myTrips = new ArrayList<>();
        this.myClients = new ArrayList<>();
        this.myReviews = new ArrayList<>();
        this.rating = 5;
    }

    public void setToken(String token) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
