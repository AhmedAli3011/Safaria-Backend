package com.safaria.backend.entity;

import com.safaria.backend.DTO.UserEditDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tour_provider")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "approval_document_path", length = 255)
    private String approvalDocumentPath;

    @Column(length = 20)
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean enabled;

    @Column(name = "country")
    private String country;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // public TourProvider(TourProviderApplicationDTO dto, String relativeFilePath, Boolean type) {

    //     this.user.setName(dto.getUsername());
    //     this.user.setEmail(dto.getEmail());
    //     this.user.setPassword(dto.getPassword());
    //     this.country = dto.getCountry();
    //     this.approvalDocumentPath = relativeFilePath;
    //     this.phone = dto.getPhone();

    // }

    // public TourProvider(Tourist tourist, Boolean type) {
    // this.username = tourist.getUsername();
    // this.email = tourist.getEmail();
    // this.password = tourist.getPassword();
    // this.country = tourist.getCountry();
    // this.type = type;
    // this.phone = tourist.getPhone();
    // this.isApproved = false;
    // this.tourismTypes = new ArrayList<>(tourist.getTourismTypes());
    // this.profilePhoto = tourist.getProfilePhoto();
    // this.date = new Date();
    // }

    // public TourProvider(Admin admin, Boolean type) {
    // this.username = admin.getUsername();
    // this.email = admin.getEmail();
    // this.password = admin.getPassword();
    // this.phone = admin.getPhone();
    // this.isApproved = false;
    // this.profilePhoto = admin.getProfilePhoto();
    // this.type = type;
    // this.date = new Date();

    // }

    // public TourProvider(UserEditDto user) {
    // this.username = user.getName();
    // this.password = user.getName() + "Password@123";
    // this.isApproved = false;
    // this.date = new Date();

    // }

}
