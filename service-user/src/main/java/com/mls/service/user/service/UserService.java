package com.mls.service.user.service;

import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.dto.request.UserUpdateRequest;
import com.mls.service.user.model.UserEntity;

import java.util.List;

public interface UserService {

    public UserEntity registerUser(UserRegisterRequest request);

    public List<UserEntity> getAllUsers();

    public UserEntity findUserById(Long id);

    public UserEntity updateUser(Long id, UserUpdateRequest request);

    public void deleteUser(Long id);


}
