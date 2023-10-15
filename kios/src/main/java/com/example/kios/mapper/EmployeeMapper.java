package com.example.kios.mapper;

import com.example.kios.model.request.EmployeeRequest;
import com.example.kios.model.response.EmployeeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    public EmployeeResponse create(EmployeeRequest request);

    public List<EmployeeResponse> getEmployee(EmployeeRequest request);

    public List<EmployeeResponse> getEmployeeByRole(EmployeeRequest request);

    public List<EmployeeResponse> getAll();

    public int update(EmployeeRequest request);

    public int delete(EmployeeRequest request);

    public int countEmployee(EmployeeRequest request);

    public int checkInfo(EmployeeRequest request);

    String getAccountId(String employeeId);
}
