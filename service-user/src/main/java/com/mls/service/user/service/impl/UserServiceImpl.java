package com.mls.service.user.service.impl;

import com.mls.service.user.client.AuthUserClient;
import com.mls.service.user.client.MatchUserClient;
import com.mls.service.user.client.PadelMatchClient;
import com.mls.service.user.dto.request.AuthUserDTO;
import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.dto.request.UserUpdateRequest;
import com.mls.service.user.dto.response.MatchUserDTO;
import com.mls.service.user.dto.response.PadelMatchDTO;
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

    @Autowired
    private PadelMatchClient padelMatchClient;

    @Autowired
    private MatchUserClient matchUserClient;

    @Autowired
    private AuthUserClient authUserClient;

    @Override
    public UserEntity registerUser(UserRegisterRequest request) {
        AuthUserDTO authUserDTO = AuthUserDTO.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        authUserClient.register(authUserDTO);

        UserEntity user = userMapper.fromUserRegisterRequestToUserEntity(request);
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

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        List<MatchUserDTO> matches = matchUserClient.getAllMatchesFromUser(id);
        for (MatchUserDTO match : matches){
            matchUserClient.removeUserFromMatch(id,match.getMatchId());
        }
        userRepository.delete(user);
    }

    @Override
    public List<UserEntity> getAllUsersByIds(List<Long> ids) {
        return userRepository.findAllById(ids);
    }


}
