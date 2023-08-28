package com.example.kios.service;

import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.BaseResponse;


public interface ITicketService {
    public BaseResponse getTicket(TicketRequest request);

    public BaseResponse getTicketForTV(TicketRequest request);

    public BaseResponse createTicket(TicketRequest request);

    public BaseResponse fakeCreateTicket(TicketRequest request);

    public BaseResponse updateTicket(TicketRequest request);

    public BaseResponse evaluateTicket(TicketRequest request);

}
