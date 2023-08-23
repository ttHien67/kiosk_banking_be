package com.example.kios.service;

import com.example.kios.model.request.ScreenRequest;
import com.example.kios.model.response.BaseResponse;


public interface IScreenService {
    public BaseResponse getScreen(ScreenRequest request);

    public BaseResponse createScreen(ScreenRequest request);

    public BaseResponse updateScreen(ScreenRequest request);

}
