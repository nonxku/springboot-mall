package com.nonxku.springbootmall.service;

import com.nonxku.springbootmall.dto.UserLoginRequest;
import com.nonxku.springbootmall.dto.UserRegisterRequest;
import com.nonxku.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
    User login(UserLoginRequest userLoginRequest);


}
