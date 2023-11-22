package com.example.webblog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "content", length = 5000, nullable = false)
    private String content;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "picture", nullable = false)
    private String picture;

    @Column(name = "brief_content", nullable = false)
    private String briefContent;

    @Column(name = "rate", nullable = false)
    private int rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
}