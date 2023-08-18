package com.example.kios.mapper;

import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.TicketResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface TicketMapper {
    public TicketResponse create(TicketRequest request);

    public List<TicketResponse> get(TicketRequest request);

    public int update(TicketRequest request);

    public int countTicket(TicketRequest request);
}
