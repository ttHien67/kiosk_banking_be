package com.example.kios.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketResponse {
    private String id;
    private String name;
    private String phone;
    private String date;
    private String time;
    private String serviceId;
    private String employeeId;
    private String code;
    private String attitude;
    private String comment;
    private int stt;
    private int status;

//  get room field in employee table
    private String room;
}
