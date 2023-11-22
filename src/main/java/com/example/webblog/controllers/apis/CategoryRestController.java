package com.example.webblog.controllers.apis;

import com.example.webblog.models.Category;
import com.example.webblog.servies.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok((List<Category>) categoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        Optional<Category> category = categoryService.findById(categoryId);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        category.setCreateAt(new Date());
        Category categoryExist = categoryService.getCategoryByName(category.getNameCategory());
        if (categoryExist != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category categoryRequest) {
        Optional<Category> category = categoryService.findById(categoryId);
        if (category.isPresent()) {
            Category categoryExist = categoryService.getCategoryByName(categoryRequest.getNameCategory());
            if (categoryExist != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            category.get().setNameCategory(categoryRequest.getNameCategory());
            return ResponseEntity.ok(categoryService.save(category.get()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        boolean isDeleted = categoryService.remove(categoryId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}