package com.example.kios.mapper;

import com.example.kios.model.request.EmployeeRequest;
import com.example.kios.model.response.EmployeeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface RoleMapper {

    EmployeeResponse create(EmployeeRequest request);

    List<String> get(@PathParam("id") String id);

    int delete(@PathParam("id") String id);
}
