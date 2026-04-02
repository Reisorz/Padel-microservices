package com.mls.service.user.service.impl;

import com.mls.service.user.dto.response.UserDTO;
import com.mls.service.user.exception.BadRequestException;
import com.mls.service.user.exception.ResourceNotFoundException;
import com.mls.service.user.model.FollowerEntity;
import com.mls.service.user.model.UserEntity;
import com.mls.service.user.repository.FollowerRepository;
import com.mls.service.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Transactional
    public void follow(Long followerId, Long followedId) {
        if (followerId.equals(followedId))
            throw new BadRequestException("No puedes seguirte a ti mismo.");

        if (followerRepository.existsByFollowerIdAndFollowedId(followerId, followedId))
            throw new BadRequestException("Ya sigues a este usuario.");

        UserEntity follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        UserEntity followed = userRepository.findById(followedId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario a seguir no encontrado"));

        FollowerEntity relationship = FollowerEntity.builder()
                .follower(follower)
                .followed(followed)
                .build();

        followerRepository.save(relationship);
    }


}