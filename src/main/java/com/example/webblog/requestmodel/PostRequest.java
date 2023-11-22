package com.example.webblog.requestmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    private String content;
    private Date createAt;
    private int status;
    private String title;
    private String picture;
    private String briefContent;
    private int rate;
    private Long accountId;
    private Long categoryId;
    private Long typeId;
}
