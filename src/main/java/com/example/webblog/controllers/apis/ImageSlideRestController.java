package com.example.webblog.controllers.apis;

import com.example.webblog.models.ImageSlide;
import com.example.webblog.requestmodel.PostRequest;
import com.example.webblog.servies.imageslide.IImageSlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/image-slides")
public class ImageSlideRestController {

    @Autowired
    private IImageSlideService imageSlideService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public ResponseEntity<List<ImageSlide>> getImageSlides() {
        return ResponseEntity.ok((List<ImageSlide>) imageSlideService.findAll());
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageSlide> getImageSlideById(@PathVariable("imageId") Long imageId) {
        Optional<ImageSlide> imageSlide = imageSlideService.findById(imageId);
        if (imageSlide.isPresent()) {
            return ResponseEntity.ok(imageSlide.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ImageSlide> createImageSlide(@ModelAttribute ImageSlide imageSlide,
                                                       @RequestParam("imgFile") MultipartFile picture)
    {
        uploadImage(picture);
        imageSlide.setLink("/img-upload/" + StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename())));
        return ResponseEntity.ok(imageSlideService.save(imageSlide));
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<ImageSlide> updateImageSlide(@PathVariable Long imageId,
               @ModelAttribute ImageSlide updatedImageSlide,
               @RequestParam("imgFile") MultipartFile picture) {

        Optional<ImageSlide> imageSlide = imageSlideService.findById(imageId);
        if (imageSlide.isPresent()) {
            uploadImage(picture);
            updatedImageSlide.setLink("/img-upload/" + StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename())));

            ImageSlide existingImageSlide = imageSlide.get();
            existingImageSlide.setUrl(updatedImageSlide.getUrl());
            existingImageSlide.setLink(updatedImageSlide.getLink());
            existingImageSlide.setDescription(updatedImageSlide.getDescription());
            return ResponseEntity.ok(imageSlideService.save(existingImageSlide));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-no-img/{imageId}")
    public ResponseEntity<ImageSlide> updateImageSlideWithoutImg(@PathVariable Long imageId,
                                                       @ModelAttribute ImageSlide updatedImageSlide) {

        Optional<ImageSlide> imageSlide = imageSlideService.findById(imageId);
        if (imageSlide.isPresent()) {
            ImageSlide existingImageSlide = imageSlide.get();
            existingImageSlide.setDescription(updatedImageSlide.getDescription());
            return ResponseEntity.ok(imageSlideService.save(existingImageSlide));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{imageId}")
    public ResponseEntity<Boolean> deleteImageSlide(@PathVariable Long imageId) {
        boolean isDeleted = imageSlideService.remove(imageId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public String uploadImage(MultipartFile picture){
        String img = "";
        if (!picture.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));

                // Define the directory to store the uploaded file
                String uploadDir = "WEB-INF/static/img-upload";

                // Get the root path of the web application
                String rootPath = servletContext.getRealPath("/");

                // Create the directory if it does not exist
                Path dirPath = Paths.get(rootPath, uploadDir);
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }

                // Construct the path where the file will be stored
                Path filePath = Paths.get(rootPath, uploadDir, fileName);

                // Save the file to the specified directory
                Files.copy(picture.getInputStream(), filePath);

                return "/img-upload/"+fileName;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}