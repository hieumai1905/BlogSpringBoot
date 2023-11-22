package com.example.webblog.servies.imageslide;

import com.example.webblog.models.ImageSlide;
import com.example.webblog.repositories.ImageSlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageSlideService implements IImageSlideService {

    private final ImageSlideRepository imageSlideRepository;

    @Autowired
    public ImageSlideService(ImageSlideRepository imageSlideRepository) {
        this.imageSlideRepository = imageSlideRepository;
    }

    @Override
    public Iterable<ImageSlide> findAll() {
        return imageSlideRepository.findAll();
    }

    @Override
    public Optional<ImageSlide> findById(Long id) {
        return imageSlideRepository.findById(id);
    }

    @Override
    public ImageSlide save(ImageSlide imageSlide) {
        return imageSlideRepository.save(imageSlide);
    }

    @Override
    public Boolean remove(Long id) {
        try {
            imageSlideRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}