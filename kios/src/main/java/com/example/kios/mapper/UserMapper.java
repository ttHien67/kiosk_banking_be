package com.example.kios.mapper;



import com.example.kios.model.request.UserRequest;
import com.example.kios.model.response.UserResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {

//    UserResponse checkEmailExistion(String email);
//
//    UserResponse create(UserRequest request);

//    UserResponse createEmployeeAccount(EmployeeRequest request);

    UserResponse finAccountByUsername(String userName);

//    UserResponse checkUsernameExistion(String userName);
}
