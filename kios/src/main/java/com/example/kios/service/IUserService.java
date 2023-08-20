package com.example.kios.service;




import com.example.kios.model.request.UserRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.model.response.UserResponse;

import javax.security.auth.message.AuthException;

public interface IUserService {

    BaseResponse createUser(UserRequest request);

    UserResponse validateUser(String userName, String password) throws AuthException;
}
