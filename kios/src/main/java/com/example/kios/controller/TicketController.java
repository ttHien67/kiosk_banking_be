package com.example.kios.controller;

import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/ticket")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    @PostMapping(value = "/getTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.getTicket(request), HttpStatus.OK);
    }

    @PostMapping(value = "/createTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> createTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.createTicket(request), HttpStatus.OK);
    }

    @PostMapping(value = "/updateTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updateTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.updateTicket(request), HttpStatus.OK);
    }

}
