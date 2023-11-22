package com.example.webblog.repositories;

import com.example.webblog.models.ImageSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageSlideRepository extends JpaRepository<ImageSlide, Long> {
}
