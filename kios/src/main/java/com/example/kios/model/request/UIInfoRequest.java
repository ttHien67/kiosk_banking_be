package com.example.kios.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UIInfoRequest {
    private String id;
    private String name;
    private String backgroundImage;
    private String createDate;
    private String editDate;
    private String uiModeId;

}
