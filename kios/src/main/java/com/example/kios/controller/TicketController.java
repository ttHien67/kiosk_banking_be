package com.example.kios.controller;

import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/ticket")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    private SimpMessagingTemplate messagingTemplate;

    public TicketController(SimpMessagingTemplate template) {
        this.messagingTemplate = template;
    }

    @PostMapping(value = "/getTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.getTicket(request), HttpStatus.OK);
    }

    @PostMapping(value = "/getTicketForTV", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getTicketForTV(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.getTicketForTV(request), HttpStatus.OK);
    }

    @SendTo("/topic/create")
    @PostMapping(value = "/createTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> createTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.createTicket(request), HttpStatus.OK);
    }

    @SendTo("/topic/create")
    @PostMapping(value = "/fakeCreateTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> fakeCreateTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.fakeCreateTicket(request), HttpStatus.OK);
    }

    @SendTo("/topic/notification")
    @PostMapping(value = "/fakeNotification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> fakeNotification(@RequestBody TicketRequest request){
        messagingTemplate.convertAndSend("/topic/notification", request);
        return new ResponseEntity<>(new BaseResponse("0", "success"), HttpStatus.OK);
    }

    @SendTo("/topic/search-ticket-for-notification")
    @PostMapping(value = "/searchTicketForNotification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> searchTicketForNotification(@RequestBody TicketRequest request){
        messagingTemplate.convertAndSend("/topic/search-ticket-for-notification", request);
        return new ResponseEntity<>(new BaseResponse("0", "success"), HttpStatus.OK);
    }

    @SendTo("/topic/update")
    @PostMapping(value = "/updateTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updateTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.updateTicket(request), HttpStatus.OK);
    }

    @PostMapping(value = "/evaluateTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> evaluateTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.evaluateTicket(request), HttpStatus.OK);
    }

}
