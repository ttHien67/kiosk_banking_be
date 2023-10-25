package com.example.kios.service.Impl;

import com.example.kios.mapper.EmployeeMapper;
import com.example.kios.mapper.TicketMapper;
import com.example.kios.model.request.TicketRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.model.response.EmployeeResponse;
import com.example.kios.model.response.TicketResponse;
import com.example.kios.service.ITicketService;
import com.example.kios.utils.ExportUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private TicketMapper mapper;

    @Autowired
    private EmployeeMapper employeeMapper;


    private SimpMessagingTemplate messagingTemplate;

    public TicketServiceImpl(SimpMessagingTemplate template) {
        this.messagingTemplate = template;
    }

    @Override
    public BaseResponse getTicket(TicketRequest request) {
        try{
            List<TicketResponse> list = mapper.get(request);

            int count = mapper.countTicket(request);

            if(list.size() >= 0){
                return new BaseResponse(list, count, "0", "get successfully");
            }else {
                return new BaseResponse("1", "get failure");
            }
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse getTicketForTV(TicketRequest request) {
        try{
            List<TicketResponse> list = mapper.getForTV(request);

            if(list.size() >= 0){
                return new BaseResponse(list, "0", "get successfully");
            }else {
                return new BaseResponse("1", "get failure");
            }
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse createTicket(TicketRequest request) {
        try{

            TicketResponse result = mapper.create(request);

            String accountId = employeeMapper.getAccountId(request.getEmployeeId());
            messagingTemplate.convertAndSend("/topic/create", result);
            messagingTemplate.convertAndSendToUser(accountId, "/private", result);

            if(result != null){
                return new BaseResponse(result, "0", "Create successfully");
            }else return new BaseResponse("1", "Create failed");

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse fakeCreateTicket(TicketRequest request) {

        messagingTemplate.convertAndSend("/topic/create", request);

        return new BaseResponse(request, "0", "Create successfully");
    }

    @Override
    public BaseResponse updateTicket(TicketRequest request) {
        try{

            int result = mapper.update(request);

            messagingTemplate.convertAndSend("/topic/update", request);

            if(result > 0){
                return new BaseResponse(result, "0", "Update successfully");
            }else return new BaseResponse("1", "Update failed");

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse evaluateTicket(TicketRequest request) {
        try{

            int result = mapper.evaluate(request);

            if(result > 0){
                return new BaseResponse(result, "0", "Update successfully");
            }else return new BaseResponse("1", "Update failed");

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse statisticMonthly(TicketRequest request) {
        try{

            Map<String, List<Integer>> result = new HashMap<>();
            List<Integer> resultDone = new ArrayList<>();
            List<Integer> resultNotStart = new ArrayList<>();
            List<Integer> resultInProgress = new ArrayList<>();

            String monthList[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

            for(String month: monthList){
                int data = mapper.statisticTicketDone(month);
                resultDone.add(data);
            }

            for(String month: monthList){
                int data = mapper.statisticTicketInProgress(month);
                resultInProgress.add(data);
            }

            for(String month: monthList){
                int data = mapper.statisticTicketNotStart(month);
                resultNotStart.add(data);
            }

            result.put("ticketDone", resultDone);
            result.put("ticketInProgress", resultInProgress);
            result.put("ticketNotStart", resultNotStart);

            return new BaseResponse(result, "0", "Statistic successfully");

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse getCountTicket(TicketRequest request) {
        try{

            Map<String, Integer> result = new HashMap<>();

            int countSumTicket = mapper.countTicket(request);
            int countNotStartTicket = mapper.countNotStartTicket(request);
            int countInProgressTicket = mapper.countInProgressTicket(request);
            int countDoneTicket = mapper.countDoneTicket(request);

            result.put("countSumTicket", countSumTicket);
            result.put("countNotStartTicket", countNotStartTicket);
            result.put("countInProgressTicket", countInProgressTicket);
            result.put("countDoneTicket", countDoneTicket);


            return new BaseResponse(result, "0", "Update successfully");

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    public BaseResponse getTicketDataForEmployee(TicketRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            ArrayList<EmployeeResponse> result = new ArrayList<EmployeeResponse>();
            List<EmployeeResponse> employees = employeeMapper.getAll();

            for(EmployeeResponse item : employees){
                result.add(mapper.statisticEmployee(item.getId(), request.getDate(), request.getMonth(), request.getYear()));
            }

            if(result.size() > 0) {
                baseResponse = new BaseResponse(result, "0", "Statistic Successfully");
            }else {
                baseResponse = new BaseResponse("1", "Statistic failure");
            }

        }catch (Exception e){
            baseResponse = new BaseResponse("1", "Failed");
            return baseResponse;
        }
        return baseResponse;
    }

    @Override
    public File export(TicketRequest request) {
        File file = null;
        try {
            file = File.createTempFile("out", ".tmp");
            file.deleteOnExit();
            Resource resource = new ClassPathResource("templates/ticket.jasper");
            try (FileOutputStream fos = new FileOutputStream(file);
                 InputStream inputStream = resource.getInputStream()) {
                List<TicketResponse> list = mapper.get(request);
                if (!list.isEmpty()) {
                    list.add(0, new TicketResponse());
                }
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("language", "");
                ExportUtil.exportReport(inputStream, fos, parameters, list, request.getFileType());
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return file;
    }



}
