package com.mls.service.user.service.impl;

import com.mls.service.user.dto.request.UserUpdateRequest;
import com.mls.service.user.mapper.UserMapper;
import com.mls.service.user.model.UserEntity;
import com.mls.service.user.repository.UserRepository;
import com.mls.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public UserEntity updateUser(Long id, UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        UserEntity updatedUser = userMapper.fromUserUpdateRequestToUserEntity(request, user);
        return userRepository.save(updatedUser);
    }

}
