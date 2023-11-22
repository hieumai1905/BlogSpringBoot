package com.example.webblog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images_slide")
@Data
@NoArgsConstructor
public class ImageSlide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "description", nullable = false)
    private String description;
}