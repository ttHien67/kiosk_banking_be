package com.example.kios.model.request;

import com.example.kios.model.response.ServiceBankingResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Getter
@Setter
public class ServiceBankingRequest extends ServiceBankingResponse {
    private int deleted;
    private String deletedDate;
    private String restoreDate;

    private int page;
    private int limit;



}
