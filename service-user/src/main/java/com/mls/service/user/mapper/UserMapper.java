package com.mls.service.user.mapper;

import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.dto.request.UserUpdateRequest;
import com.mls.service.user.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserEntity fromUserUpdateRequestToUserEntity(UserUpdateRequest request, UserEntity userEntity){
        userEntity.setName(request.getName());
        userEntity.setCity(request.getCity());
        userEntity.setPadelLevel(request.getPadelLevel());
        userEntity.setPreferredSide(request.getPreferredSide());
        return userEntity;
    }

    public UserEntity fromUserRegisterRequestToUserEntity(UserRegisterRequest request) {
        UserEntity user = UserEntity.builder()
                .city(request.getCity())
                .email(request.getEmail())
                .name(request.getName())
                .padelLevel(request.getPadelLevel())
                .build();
        return user;
    }
}

