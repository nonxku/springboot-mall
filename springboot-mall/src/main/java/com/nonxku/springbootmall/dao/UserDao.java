package com.nonxku.springbootmall.dao;

import com.nonxku.springbootmall.dto.UserRegisterRequest;
import com.nonxku.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);


}
