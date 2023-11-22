package com.example.webblog.mappers;

import com.example.webblog.models.Post;
import com.example.webblog.requestmodel.PostRequest;

public class PostMapper {
    public static PostRequest postModelToPostRequest(Post post) {
        PostRequest postRequest = new PostRequest();
        postRequest.setPostId(post.getPostId());
        postRequest.setContent(post.getContent());
        postRequest.setCreateAt(post.getCreateAt());
        postRequest.setStatus(post.getStatus());
        postRequest.setTitle(post.getTitle());
        postRequest.setPicture(post.getPicture());
        postRequest.setBriefContent(post.getBriefContent());
        postRequest.setRate(post.getRate());
        postRequest.setAccountId(post.getAccount().getAccountId());
        postRequest.setCategoryId(post.getCategory().getCategoryId());
        postRequest.setTypeId(post.getType().getTypeId());
        return postRequest;
    }
}
