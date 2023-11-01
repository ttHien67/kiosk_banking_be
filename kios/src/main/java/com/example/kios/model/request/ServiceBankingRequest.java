package com.example.kios.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceBankingRequest {
    private String id;
    private String name;
    private String image;
    private String description;
    private int deleted;
    private String deletedDate;
    private String restoreDate;

    private int page;
    private int limit;

}
