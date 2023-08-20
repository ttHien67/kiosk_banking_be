package com.example.kios.service;

import com.example.kios.model.request.ServiceBankingRequest;
import com.example.kios.model.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IServiceBankingService {

    public BaseResponse createService(ServiceBankingRequest request);

    public BaseResponse getService(ServiceBankingRequest request);

    public BaseResponse updateService(ServiceBankingRequest request);

    public BaseResponse deleteService(ServiceBankingRequest request);
}
