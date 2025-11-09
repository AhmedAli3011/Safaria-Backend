package com.safaria.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private TourProvider tourProvider;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Tourist tourist;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert your role to Spring Security's authority format
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email; // or user.getUsername(), depending on your system
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // change if you want account expiration logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // change if you track locked accounts
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // change if you track credential expiry
    }

    @Override
    public boolean isEnabled() {
        // Allow non-tour-provider users to login by default.
        if (this.role == Role.TOUR_PROVIDER) {
            return this.tourProvider != null && this.tourProvider.isEnabled();
        }
        return true;
    }

    

    public String getEmail() {
        return this.email;
    }

}
