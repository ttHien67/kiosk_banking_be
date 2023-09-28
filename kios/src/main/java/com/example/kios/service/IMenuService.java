package com.example.kios.service;

import com.example.kios.model.request.MenuRequest;
import com.example.kios.model.response.BaseResponse;

public interface IMenuService {

    BaseResponse getMenu(MenuRequest request);

    BaseResponse getMenuByRole(MenuRequest request);

    BaseResponse updateMenu(MenuRequest request);

    BaseResponse createMenu(MenuRequest request);

    BaseResponse deleteMenu(MenuRequest request);
}
