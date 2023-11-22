package com.example.webblog.requestmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequest {
    private Long commentId;
    private Long postId;
    private Long accountId;
    private String content;
    private String commentAt;
}
