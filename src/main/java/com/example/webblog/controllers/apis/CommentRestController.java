package com.example.webblog.controllers.apis;

import com.example.webblog.mappers.CommentMapper;
import com.example.webblog.models.Post;
import com.example.webblog.requestmodel.CommentRequest;
import com.example.webblog.requestmodel.PostRequest;
import com.example.webblog.servies.comment.ICommentService;
import com.example.webblog.servies.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentRestController {
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPostService postService;

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<CommentRequest>> getCommentByPostId(@PathVariable Long postId) {
        PostRequest postComment = postService.findById(postId).orElse(null);
        if(postComment == null){
            return ResponseEntity.notFound().build();
        }
        List<CommentRequest> comments = commentService.getCommentByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/posts/{postId}")
    public ResponseEntity<CommentRequest> createComment(@PathVariable Long postId, @RequestBody CommentRequest commentRequest) {
        PostRequest postComment = postService.findById(postId).orElse(null);
        if(postComment == null){
            return ResponseEntity.notFound().build();
        }
        commentRequest.setPostId(postId);
        CommentRequest comment = commentService.save(commentRequest);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentRequest> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        CommentRequest comment = commentService.findById(commentId).orElse(null);
        if(comment == null){
            return ResponseEntity.notFound().build();
        }
        commentRequest.setCommentId(commentId);
        CommentRequest commentUpdate = commentService.save(commentRequest);
        return ResponseEntity.ok(commentUpdate);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable("commentId") Long commentId) {
        CommentRequest commentRequest = commentService.findById(commentId).orElse(null);
        if(commentRequest == null){
            return ResponseEntity.notFound().build();
        }
        boolean isDeleted = commentService.remove(commentId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
