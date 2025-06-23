package com.mls.service.user.service.impl;

import com.mls.service.user.client.AuthUserClient;
import com.mls.service.user.client.MatchUserClient;
import com.mls.service.user.client.PadelMatchClient;
import com.mls.service.user.dto.request.AuthUserDTO;
import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.dto.request.UserUpdateRequest;
import com.mls.service.user.dto.response.MatchUserDTO;
import com.mls.service.user.dto.response.PadelMatchDTO;
import com.mls.service.user.exception.ResourceNotFoundException;
import com.mls.service.user.mapper.UserMapper;
import com.mls.service.user.model.UserEntity;
import com.mls.service.user.repository.UserRepository;
import com.mls.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MatchUserClient matchUserClient;

    @Autowired
    private AuthUserClient authUserClient;

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("png", "jpg", "jpeg", "gif");
    private static final List<String> ALLOWED_MIMES = Arrays.asList("image/png", "image/jpg", "image/jpeg", "image/gif");

    @Value("${file.upload-dir}")
    private String uploadDir;


    @Override
    public UserEntity registerUser(UserRegisterRequest request) {
        AuthUserDTO authUserDTO = AuthUserDTO.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        authUserClient.register(authUserDTO);

        UserEntity user = userMapper.fromUserRegisterRequestToUserEntity(request);
        user.setAvatarImageUrl("/assets/avatar-images/no-avatar.png");
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public UserEntity updateUser(Long id, UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        UserEntity updatedUser = userMapper.fromUserUpdateRequestToUserEntity(request, user);
        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
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

    @Override
    public String uploadAvatarImage(MultipartFile file, Long userId) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("File is empty");
        }

        // Validate MIME
        String mime = file.getContentType();
        if (!ALLOWED_MIMES.contains(mime)) {
            throw new Exception("MIME type not allowed: " + mime);
        }

        // Validate extension
        String originalName = file.getOriginalFilename();
        String extension = "";
        int idx = originalName.lastIndexOf('.');
        if (idx > 0) {
            extension = originalName.substring(idx + 1).toLowerCase();
        }
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new Exception("Extension not allowed: " + extension);
        }

        String baseName = userId + "_avatar_image";

        // Create folder if it does not exist
        Path uploadsPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        if (!Files.exists(uploadsPath)) {
            Files.createDirectories(uploadsPath);
        }

        // Delete previous avatar image
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadsPath, baseName + ".*")) {
            for (Path existingFile : stream) {
                Files.deleteIfExists(existingFile);
            }
        } catch (IOException e) {
            System.err.println("Warning: failed to delete existing avatar files: " + e.getMessage());
        }

        String newFileName = baseName + "." + extension;
        Path filePath = uploadsPath.resolve(newFileName);

        // Save file in folder
        try {
            file.transferTo(filePath.toFile());
        } catch (IOException | IllegalStateException e) {
            throw new Exception("Error while storing file: " + e.getMessage(), e);
        }

        // Add path to user atribute
        UserEntity user = findUserById(userId);
        user.setAvatarImageUrl("/assets/avatar-images/" + newFileName);

        return userRepository.save(user).getAvatarImageUrl();
    }


}
