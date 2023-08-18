package com.example.kios.service.Impl;

import com.example.kios.mapper.TicketMapper;
import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.model.response.TicketResponse;
import com.example.kios.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private TicketMapper mapper;

    @Override
    public BaseResponse getTicket(TicketRequest request) {
        try{
            List<TicketResponse> list = mapper.get(request);

            int count = mapper.countTicket(request);

            if(list.size() > 0){
                return new BaseResponse(list, count, "0", "get successfully");
            }else {
                return new BaseResponse("1", "get failure");
            }
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse createTicket(TicketRequest request) {
        try{

            TicketResponse result = mapper.create(request);

            if(result != null){
                return new BaseResponse(result, "0", "Create successfully");
            }else return new BaseResponse("1", "Create failed");

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse updateTicket(TicketRequest request) {
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
