package com.example.kios.service.Impl;

import com.example.kios.mapper.UIInfoMapper;
import com.example.kios.model.request.UIInfoRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.model.response.UIInfoResponse;
import com.example.kios.service.IUIInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UIInfoServiceImpl implements IUIInfoService {

    @Autowired
    private UIInfoMapper mapper;

    @Override
    public BaseResponse getAll(UIInfoRequest request) {
        try{
            List<UIInfoResponse> list = mapper.getAll(request);

            if(list.size() > 0){
                return new BaseResponse(list, "0", "get successfully");
            }else return new BaseResponse("1", "get failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse getUiById(UIInfoRequest request) {
        try{
            List<UIInfoResponse> list = mapper.getUiById(request);

            if(list.size() > 0){
                return new BaseResponse(list, "0", "get successfully");
            }else return new BaseResponse("1", "get failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse updateUi(UIInfoRequest request) {
        try{
            int result = mapper.update(request);

            if(result > 0){
                return new BaseResponse(result, "0", "update successfully");
            }else return new BaseResponse("1", "update failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse createUi(UIInfoRequest request) {
        try{
            UIInfoResponse result = mapper.create(request);

            if(result != null){
                return new BaseResponse(result, "0", "create successfully");
            }else return new BaseResponse("1", "create failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse deleteUi(UIInfoRequest request) {
        try{
            int result = mapper.delete(request);

            if(result > 0){
                return new BaseResponse(result, "0", "delete successfully");
            }else return new BaseResponse("1", "delete failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }
}
