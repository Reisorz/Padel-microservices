package com.mls.service.auth.service;

import com.mls.service.auth.entity.AuthUserEntity;
import com.mls.service.auth.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    public Optional<AuthUserEntity> findAuthUserEntityByEmail(String email){
        return authUserRepository.findAuthUserEntityByEmail(email);
    }
}
