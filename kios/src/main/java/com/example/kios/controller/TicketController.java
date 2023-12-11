package com.example.kios.controller;

import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.service.ITicketService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

    @PostMapping(value = "/statisticMonthly", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> statisticMonthly(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.statisticMonthly(request), HttpStatus.OK);
    }

    @PostMapping(value = "/getCountTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getCountTicket(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.getCountTicket(request), HttpStatus.OK);
    }

    @PostMapping(value = "/getTicketDataForEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getTicketDataForEmployee(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.getTicketDataForEmployee(request), HttpStatus.OK);
    }

    @PostMapping(value = "/countTicketForEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> countTicketForEmployee(@RequestBody TicketRequest request){
        return new ResponseEntity<>(ticketService.countTicketForEmployee(request), HttpStatus.OK);
    }

    @PostMapping(value = "/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> export(
            @RequestBody TicketRequest request) {
        try {
            File file = ticketService.export(request);
            if (file == null) {
                throw new ServiceException("Nothing to export");
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().headers(new HttpHeaders()).contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/" + request.getFileType())).body(resource);
        } catch (FileNotFoundException e) {
            throw new ServiceException("Failed");
        }
    }

}
