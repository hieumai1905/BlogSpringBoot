package com.example.webblog.servies.comment;

import com.example.webblog.requestmodel.CommentRequest;
import com.example.webblog.servies.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<CommentRequest> {
    List<CommentRequest> getCommentByPostId(Long postId);

    Boolean removeAllByPost(Long postId);

    Boolean removeAllByAccount(Long accountId);
}
