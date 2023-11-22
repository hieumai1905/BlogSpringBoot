package com.example.webblog.mappers;

import com.example.webblog.models.Rate;
import com.example.webblog.requestmodel.RateRequest;

public class RateMapper {
    public static RateRequest rateModelToRateRequest(Rate rate) {
        RateRequest rateRequest = new RateRequest();
        rateRequest.setPostId(rate.getPost().getPostId());
        rateRequest.setAccountId(rate.getAccount().getAccountId());
        rateRequest.setValue(rate.getValue());
        return rateRequest;
    }
}
