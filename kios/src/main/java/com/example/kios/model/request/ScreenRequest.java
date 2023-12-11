package com.example.kios.model.request;

import com.example.kios.model.response.ScreenResponse;
import lombok.Data;

import java.io.File;

@Data
public class ScreenRequest extends ScreenResponse {
    private int limit;
    private int page;
}
