package com.example.kios.service;


import com.example.kios.model.request.EmployeeRequest;
import com.example.kios.model.response.BaseResponse;

public interface IEmployeeService {

    public BaseResponse getEmployee(EmployeeRequest request);

    public BaseResponse getAllEmployee(EmployeeRequest request);

    public BaseResponse updateEmployee(EmployeeRequest request);

    public BaseResponse deleteEmployee(EmployeeRequest request);

    public BaseResponse createEmployee(EmployeeRequest request);

    public BaseResponse restoreEmployee(EmployeeRequest request);

    public BaseResponse removeEmployee(EmployeeRequest request);


}
