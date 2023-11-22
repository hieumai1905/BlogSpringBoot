package com.example.webblog.servies.post;

import com.example.webblog.requestmodel.PostRequest;
import com.example.webblog.servies.IGeneralService;

import java.util.List;

public interface IPostService extends IGeneralService<PostRequest> {
    List<PostRequest> getAllPostByTypeId(Long typeId);

    List<PostRequest> getAllPostByCategoryId(Long categoryId);

    List<PostRequest> getAllPostByAccountId(Long accountId);

    List<PostRequest> getAllPostByTitle(String title);

    Boolean activePost(Long id);
}
