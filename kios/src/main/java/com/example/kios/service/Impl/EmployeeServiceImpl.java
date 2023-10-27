package com.example.kios.service.Impl;

import com.example.kios.mapper.EmployeeMapper;
import com.example.kios.mapper.RoleMapper;
import com.example.kios.mapper.UserMapper;
import com.example.kios.model.request.EmployeeRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.model.response.EmployeeResponse;
import com.example.kios.model.response.RoleResponse;
import com.example.kios.model.response.UserResponse;
import com.example.kios.service.ICommonService;
import com.example.kios.service.IEmployeeService;
import com.google.common.base.Strings;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ICommonService commonService;

    public BaseResponse getEmployee(EmployeeRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            List<EmployeeResponse> result;
            if(request.getRole() != null){
                result = mapper.getEmployeeByRole(request);
            }else {
                result = mapper.getEmployee(request);
            }

            for(EmployeeResponse item : result){
                List<String> listRoleOfEmployee = roleMapper.get(item.getId());
                if(listRoleOfEmployee != null){
                    item.setRole(listRoleOfEmployee);
                }
            }
            int count = mapper.countEmployee(request);

            if(result.size() >= 0) {
                baseResponse = new BaseResponse(result, count, "0", "Get Successfully");
            }

        }catch (Exception e){
            baseResponse = new BaseResponse("1", "Failed");
            return baseResponse;
        }
        return baseResponse;
    }

    public BaseResponse getAllEmployee(EmployeeRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            List<EmployeeResponse> result = mapper.getAll();

            if(result.size() > 0) {
                baseResponse = new BaseResponse(result, "0", "Get Successfully");
            }

        }catch (Exception e){
            baseResponse = new BaseResponse("1", "Failed");
            return baseResponse;
        }
        return baseResponse;
    }

    public BaseResponse updateEmployee(EmployeeRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            int deleteOldRole = roleMapper.delete(request.getId());
            if(deleteOldRole > 0){

                int result = mapper.update(request);

                if(result > 0) {
                    EmployeeResponse roleResult = roleMapper.create(request);
                    baseResponse = new BaseResponse(request, "0", "Update Successfully");
                }else {
                    baseResponse = new BaseResponse("1", "Update failure");
                }

            }

        }catch (Exception e){
            baseResponse = new BaseResponse("1", "Failed");
            return baseResponse;
        }
        return baseResponse;
    }

    public BaseResponse deleteEmployee(EmployeeRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            int result = mapper.delete(request);

            if(result > 0) {
                baseResponse = new BaseResponse(request, "0", "Delete Successfully");
            }else {
                baseResponse = new BaseResponse("1", "Delete failure");
            }

        }catch (Exception e){
            baseResponse = new BaseResponse("1", "Failed");
            return baseResponse;
        }
        return baseResponse;
    }

    public BaseResponse createEmployee(EmployeeRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        int index = 0;
        try{
//            int checkEmployeeCode = mapper.checkInfo(request);

//            if(checkEmployeeCode > 0) {
//                return new BaseResponse("1", "Employee code has be use");
//            }

//            create account
            if (request == null || Strings.isNullOrEmpty(request.getName())
                    || Strings.isNullOrEmpty(request.getUserName())
                    || Strings.isNullOrEmpty(request.getPassword())) {
                return new BaseResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        "Fiels is requried");
            }

            UserResponse checkUsernameExistion = userMapper.checkUsernameExistion(request.getUserName());
            if(checkUsernameExistion != null) {
                return new BaseResponse("1", "Username already in use");
            }

            String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(10));
            request.setPassword(hashedPassword);

            UserResponse account = userMapper.createEmployeeAccount(request);

            if(account != null) {
                request.setAccountId(account.getId());
            }else {
                return new BaseResponse("1", "Create failed");
            }

            String code = "EMP-";
            int getId = mapper.getId() + index;
            String pad = commonService.padLeft(String.valueOf(getId), 4, "0");

            request.setCode(code + pad);

            EmployeeResponse result = mapper.create(request);
            if(result != null) {
                request.setId(result.getId());
                EmployeeResponse roleResult = roleMapper.create(request);
                if (result != null) {
                    baseResponse = new BaseResponse(request, "0", "Create Successfully");
                } else {
                    baseResponse = new BaseResponse("1", "Create failure");
                }
            }else {
                return new BaseResponse("1", "create failure");
            }

        }catch (Exception e){
            baseResponse = new BaseResponse("1", "Failed");
            return baseResponse;
        }
        return baseResponse;
    }
}
