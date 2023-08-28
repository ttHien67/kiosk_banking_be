package com.example.kios.mapper;

import com.example.kios.model.request.ScreenRequest;
import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.ScreenResponse;
import com.example.kios.model.response.TicketResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScreenMapper {
    public ScreenResponse create(ScreenRequest request);

    public List<ScreenResponse> get(ScreenRequest request);

    public int update(ScreenRequest request);

    public int delete(ScreenRequest request);

    public int countScreen(ScreenRequest request);

    int checkDateExisted(ScreenRequest request);

    int checkTimeExisted(ScreenRequest request);
}
