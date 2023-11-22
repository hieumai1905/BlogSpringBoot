package com.example.webblog.servies.rate;

import com.example.webblog.requestmodel.RateRequest;
import com.example.webblog.servies.IGeneralService;

import java.util.List;

public interface IRateService extends IGeneralService<RateRequest> {
    List<RateRequest> getRateByPostId(Long postId);

    Boolean removeAllByPost(Long postId);

    Boolean removeAllByAccount(Long accountId);
}
