package com.example.kios.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceBankingResponse {
    private String id;
    private String name;
    private String image;
    private String description;
    private String editDate;
    private String createDate;
}
