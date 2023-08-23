package com.example.kios.service.Impl;

import com.example.kios.mapper.ScreenMapper;
import com.example.kios.model.request.ScreenRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.model.response.ScreenResponse;
import com.example.kios.service.IScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenServiceImpl implements IScreenService {

    @Autowired
    private ScreenMapper mapper;

    @Override
    public BaseResponse getScreen(ScreenRequest request) {
        try{
            List<ScreenResponse> list = mapper.get(request);

            int count = mapper.countScreen(request);

            if(list.size() >= 0){
                return new BaseResponse(list, count, "0", "get successfully");
            }else {
                return new BaseResponse("1", "get failure");
            }
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse createScreen(ScreenRequest request) {
        try{

            ScreenResponse result = mapper.create(request);

            if(result != null){
                return new BaseResponse(result, "0", "Create successfully");
            }else return new BaseResponse("1", "Create failed");

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse updateScreen(ScreenRequest request) {
        try{

            int result = mapper.update(request);

            if(result > 0){
                return new BaseResponse(result, "0", "Update successfully");
            }else return new BaseResponse("1", "Update failed");

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }
}