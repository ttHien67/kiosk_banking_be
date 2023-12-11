package com.example.kios.mapper;

import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.EmployeeResponse;
import com.example.kios.model.response.TicketResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface TicketMapper {
    public TicketResponse create(TicketRequest request);

    public List<TicketResponse> get(TicketRequest request);

    public List<TicketResponse> getForTV(TicketRequest request);

    public int update(TicketRequest request);

    public int evaluate(TicketRequest request);

    public int countTicket(TicketRequest request);

    public int statisticTicketDone(String moth);

    public int statisticTicketInProgress(String moth);

    public int statisticTicketNotStart(String moth);

    public int countNotStartTicket(TicketRequest request);

    public int countInProgressTicket(TicketRequest request);

    public int countDoneTicket(TicketRequest request);

    List<TicketResponse> countTicketForEmployee(TicketRequest request);

    public EmployeeResponse statisticEmployee(@Param("id") String id, @Param("date")String date,
                                              @Param("month")String month, @Param("year")String year);
}
