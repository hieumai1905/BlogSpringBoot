package com.example.webblog.repositories;

import com.example.webblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> getAllByAccountAccountId(Long accountId);
    Iterable<Post> getAllByCategoryCategoryId(Long categoryId);
    Iterable<Post> getAllByTypeTypeId(Long typeId);
    Iterable<Post> getAllByTitleContaining(String title);
}
