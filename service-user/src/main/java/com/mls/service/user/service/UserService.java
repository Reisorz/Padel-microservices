package com.mls.service.user.service;

import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.dto.request.UserUpdateRequest;
import com.mls.service.user.model.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public UserEntity registerUser(UserRegisterRequest request);

    public List<UserEntity> getAllUsers();

    public UserEntity findUserById(Long id);

    public UserEntity updateUser(Long id, UserUpdateRequest request);

    public void deleteUser(Long id);

    public List<UserEntity> getAllUsersByIds(List<Long> ids);

    public String uploadAvatarImage(MultipartFile file, Long userId) throws Exception;


}
