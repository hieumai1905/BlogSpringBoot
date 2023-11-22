package com.example.webblog.requestmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RateRequest {
    private Long postId;
    private Long accountId;
    private int value;
}
