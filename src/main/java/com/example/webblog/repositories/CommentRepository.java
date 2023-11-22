package com.example.webblog.repositories;

import com.example.webblog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByPostPostId(Long postId);
    Boolean removeAllByPostPostId(Long postId);
    List<Comment> getAllByAccountAccountId(Long accountId);
}
