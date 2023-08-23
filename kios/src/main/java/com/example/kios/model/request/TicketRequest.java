package com.example.kios.model.request;

import com.example.kios.model.dto.Paging;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketRequest extends Paging {
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

    private String accountId;
}
