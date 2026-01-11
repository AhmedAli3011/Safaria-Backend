package com.safaria.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Image")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageID")
    private Integer imageId;

    @Column(name = "ImageUrl", nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "TourID", nullable = false)
    private Tour tour;
}
