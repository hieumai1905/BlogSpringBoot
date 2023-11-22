package com.example.webblog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name_category", nullable = false)
    private String nameCategory;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
        this.createAt = new Date();
    }
}