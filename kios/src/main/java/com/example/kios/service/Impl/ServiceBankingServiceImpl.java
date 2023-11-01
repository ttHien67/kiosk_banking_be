package com.example.kios.service.Impl;

import com.example.kios.mapper.ServiceBankingMapper;
import com.example.kios.model.request.ServiceBankingRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.model.response.ServiceBankingResponse;
import com.example.kios.service.IServiceBankingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ServiceBankingServiceImpl implements IServiceBankingService {
    @Autowired
    private ServiceBankingMapper mapper;

    @Override
    public BaseResponse createService(ServiceBankingRequest request) {
        try {
            ServiceBankingResponse result = mapper.create(request);

            if(result != null){
                return new BaseResponse(result, "0", "create successfully");
            }
            return new BaseResponse("1", "create failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse getService(ServiceBankingRequest request) {
        try {
            List<ServiceBankingResponse> list = mapper.get(request);

            int count = mapper.countService(request);

            if(list != null){
                return new BaseResponse(list, count, "0", "get successfully");
            }
            return new BaseResponse("1", "get failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse updateService(ServiceBankingRequest request) {
        try {
            int result = mapper.update(request);

            if(result > 0){
                return new BaseResponse(result, "0", "update successfully");
            }
            return new BaseResponse("1", "update failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse deleteService(ServiceBankingRequest request) {
        try {
            int result = mapper.delete(request);

            if(result > 0){
                return new BaseResponse(result, "0", "delete successfully");
            }
            return new BaseResponse("1", "delete failure");
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse restoreService(ServiceBankingRequest request) {
        try{
            int result = mapper.restore(request);

            if(result > 0){
                return new BaseResponse("0", "Restore successfully");
            }else {
                return new BaseResponse("1", "Restore failure");
            }
        }catch (Exception e) {
            return new BaseResponse("-1", "Fail");
        }
    }

    @Override
    public BaseResponse removeService(ServiceBankingRequest request) {
        try{
            int result = mapper.remove(request);

            if(result > 0){
                return new BaseResponse("0", "Restore successfully");
            }else {
                return new BaseResponse("1", "Restore failure");
            }
        }catch (Exception e) {
            return new BaseResponse("-1", "Fail");
        }
    }


}
