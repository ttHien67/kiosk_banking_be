package com.example.kios.mapper;

import com.example.kios.model.request.ServiceBankingRequest;
import com.example.kios.model.response.ServiceBankingResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceBankingMapper {

    ServiceBankingResponse create(ServiceBankingRequest request);

    List<ServiceBankingResponse> get(ServiceBankingRequest request);

    int countService(ServiceBankingRequest request);

    int update(ServiceBankingRequest request);

    int delete(ServiceBankingRequest request);
}
