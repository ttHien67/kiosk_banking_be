package com.example.kios.model.request;

import com.example.kios.model.dto.Paging;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenRequest extends Paging {
    private String id;
    private String name;
    private String image;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String createDate;
    private String editDate;
}
