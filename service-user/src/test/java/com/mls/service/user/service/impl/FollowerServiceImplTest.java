package com.mls.service.user.service.impl;

import com.mls.service.user.dto.response.UserDTO;
import com.mls.service.user.exception.BadRequestException;
import com.mls.service.user.exception.ResourceNotFoundException;
import com.mls.service.user.model.FollowerEntity;
import com.mls.service.user.model.PreferredSide;
import com.mls.service.user.model.UserEntity;
import com.mls.service.user.repository.FollowerRepository;
import com.mls.service.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FollowerServiceImplTest {

    @Mock
    private FollowerRepository followerRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FollowerServiceImpl followerService;

    @Nested
    @DisplayName("Pruebas para el método follow")
    class FollowMethodTests {

        @Test
        @DisplayName("Debe lanzar BadRequestException cuando un usuario intenta seguirse a sí mismo")
        void follow_SelfFollow_ThrowsBadRequestException() {
            Long userId = 1L;

            BadRequestException exception = assertThrows(BadRequestException.class, () ->
                    followerService.follow(userId, userId)
            );

            assertEquals("No puedes seguirte a ti mismo.", exception.getMessage());
            verifyNoInteractions(followerRepository, userRepository);
        }

        @Test
        @DisplayName("Debe lanzar BadRequestException cuando ya existe la relación de seguimiento")
        void follow_AlreadyFollowing_ThrowsBadRequestException() {
            Long followerId = 1L;
            Long followedId = 2L;

            when(followerRepository.existsByFollowerIdAndFollowedId(followerId, followedId)).thenReturn(true);

            BadRequestException exception = assertThrows(BadRequestException.class, () ->
                    followerService.follow(followerId, followedId)
            );

            assertEquals("Ya sigues a este usuario.", exception.getMessage());
            verify(followerRepository).existsByFollowerIdAndFollowedId(followerId, followedId);
            verifyNoInteractions(userRepository);
        }

        @Test
        @DisplayName("Debe lanzar ResourceNotFoundException cuando el usuario seguidor no existe")
        void follow_FollowerNotFound_ThrowsResourceNotFoundException() {
            Long followerId = 1L;
            Long followedId = 2L;

            when(followerRepository.existsByFollowerIdAndFollowedId(followerId, followedId)).thenReturn(false);
            when(userRepository.findById(followerId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                    followerService.follow(followerId, followedId)
            );

            assertEquals("Usuario no encontrado", exception.getMessage());
            verify(userRepository).findById(followerId);
            verify(userRepository, never()).findById(followedId);
            verify(followerRepository, never()).save(any());
        }

        @Test
        @DisplayName("Debe lanzar ResourceNotFoundException cuando el usuario a seguir no existe")
        void follow_FollowedNotFound_ThrowsResourceNotFoundException() {
            Long followerId = 1L;
            Long followedId = 2L;
            UserEntity follower = new UserEntity();

            when(followerRepository.existsByFollowerIdAndFollowedId(followerId, followedId)).thenReturn(false);
            when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
            when(userRepository.findById(followedId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                    followerService.follow(followerId, followedId)
            );

            assertEquals("Usuario a seguir no encontrado", exception.getMessage());
            verify(userRepository).findById(followerId);
            verify(userRepository).findById(followedId);
            verify(followerRepository, never()).save(any());
        }

        @Test
        @DisplayName("Debe guardar la relación correctamente cuando los datos son válidos")
        void follow_ValidScenario_SavesRelationship() {
            Long followerId = 1L;
            Long followedId = 2L;

            UserEntity follower = UserEntity.builder().id(followerId).name("Follower").build();
            UserEntity followed = UserEntity.builder().id(followedId).name("Followed").build();

            when(followerRepository.existsByFollowerIdAndFollowedId(followerId, followedId)).thenReturn(false);
            when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
            when(userRepository.findById(followedId)).thenReturn(Optional.of(followed));

            followerService.follow(followerId, followedId);

            ArgumentCaptor<FollowerEntity> entityCaptor = ArgumentCaptor.forClass(FollowerEntity.class);
            verify(followerRepository).save(entityCaptor.capture());

            FollowerEntity savedRelationship = entityCaptor.getValue();
            assertEquals(follower, savedRelationship.getFollower());
            assertEquals(followed, savedRelationship.getFollowed());
        }
    }

    @Nested
    @DisplayName("Pruebas para el método getFollowersByUserId")
    class GetFollowersByUserIdTests {

        @Test
        @DisplayName("Debe retornar una página de UserDTO mapeada correctamente con orden descendente por createdAt")
        void getFollowersByUserId_ValidScenario_ReturnsMappedPage() {
            Long userId = 1L;
            int page = 0;
            int size = 10;

            UserEntity followerUser = UserEntity.builder()
                    .id(2L)
                    .name("John Doe")
                    .city("Madrid")
                    .padelLevel(2.5)
                    .preferredSide(PreferredSide.valueOf("LEFT"))
                    .avatarImageUrl("http://avatar.url")
                    .build();

            FollowerEntity followerEntity = FollowerEntity.builder()
                    .follower(followerUser)
                    .build();

            Pageable expectedPageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<FollowerEntity> mockPage = new PageImpl<>(Collections.singletonList(followerEntity), expectedPageable, 1);

            when(followerRepository.findByFollowedId(userId, expectedPageable)).thenReturn(mockPage);

            Page<UserDTO> result = followerService.getFollowersByUserId(userId, page, size);

            assertNotNull(result);
            assertEquals(1, result.getTotalElements());

            UserDTO dto = result.getContent().getFirst();
            assertEquals(followerUser.getId(), dto.getId());
            assertEquals(followerUser.getName(), dto.getName());
            assertEquals(followerUser.getCity(), dto.getCity());
            assertEquals(followerUser.getPadelLevel(), dto.getPadelLevel());
            assertEquals(followerUser.getPreferredSide(), dto.getPreferredSide());
            assertEquals(followerUser.getAvatarImageUrl(), dto.getAvatarImageUrl());

            verify(followerRepository).findByFollowedId(userId, expectedPageable);
        }
    }
}