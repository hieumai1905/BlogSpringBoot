package com.example.webblog.mappers;

import com.example.webblog.models.Comment;
import com.example.webblog.requestmodel.CommentRequest;


public class CommentMapper {
    public static CommentRequest commentModelToCommentRequest(Comment comment) {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentId(comment.getCommentId());
        commentRequest.setContent(comment.getContent());
        commentRequest.setCommentAt(comment.getCommentAt().toString());
        commentRequest.setAccountId(comment.getAccount().getAccountId());
        commentRequest.setPostId(comment.getPost().getPostId());
        return commentRequest;
    }
}
