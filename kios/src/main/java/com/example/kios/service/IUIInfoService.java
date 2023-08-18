package com.example.kios.service;

import com.example.kios.controller.UIInfo;
import com.example.kios.model.request.UIInfoRequest;
import com.example.kios.model.response.BaseResponse;

public interface IUIInfoService {

    public BaseResponse getUiById(UIInfoRequest request);
    public BaseResponse getAll(UIInfoRequest request);
    public BaseResponse updateUi(UIInfoRequest request);
    public BaseResponse createUi(UIInfoRequest request);
    public BaseResponse deleteUi(UIInfoRequest request);
}
