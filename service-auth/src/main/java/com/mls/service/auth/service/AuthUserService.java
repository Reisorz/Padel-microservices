package com.mls.service.auth.service;

import com.mls.service.auth.dto.AuthUserDTO;
import com.mls.service.auth.dto.TokenDTO;
import com.mls.service.auth.model.AuthUserEntity;
import com.mls.service.auth.repository.AuthUserRepository;
import com.mls.service.auth.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public AuthUserEntity register(AuthUserDTO authUserDTO) {
        Optional<AuthUserEntity> authUserEntity = authUserRepository.findAuthUserEntityByEmail(authUserDTO.getEmail());
        if(authUserEntity.isPresent()){
            return null;
        }
        AuthUserEntity authUserRegister = AuthUserEntity.builder()
                .email(authUserDTO.getEmail())
                .password(passwordEncoder.encode(authUserDTO.getPassword()))
                .build();
        return authUserRepository.save(authUserRegister);
    }

    public TokenDTO login(AuthUserDTO authUserDTO) {
        Optional<AuthUserEntity> authUserEntity = authUserRepository.findAuthUserEntityByEmail(authUserDTO.getEmail());
        if(authUserEntity.isEmpty()){
            return null;
        }
        if(passwordEncoder.matches(authUserDTO.getPassword(), authUserEntity.get().getPassword())) {
            TokenDTO token = TokenDTO.builder()
                    .token(jwtProvider.buildToken(authUserEntity.get()))
                    .build();
            return token;
        }
        return null;
    }

    public TokenDTO validate(String token){
        if(!jwtProvider.isTokenValid(token)){
            return null;
        }
        String email = jwtProvider.extractEmail(token);
        if(authUserRepository.findAuthUserEntityByEmail(email).isEmpty()){
            return null;
        }
        return new TokenDTO(token);
    }






}
