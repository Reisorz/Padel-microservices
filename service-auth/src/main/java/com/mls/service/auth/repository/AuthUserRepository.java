package com.mls.service.auth.repository;

import com.mls.service.auth.entity.AuthUserEntity;
import com.mls.service.auth.service.AuthUserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUserEntity,Long> {

    public Optional<AuthUserEntity> findAuthUserEntityByEmail(String email);
}
