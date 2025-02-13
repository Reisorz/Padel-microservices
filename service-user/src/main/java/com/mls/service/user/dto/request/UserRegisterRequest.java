package com.mls.service.user.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequest {

    private String name;

    private String email;

    private String password;

    private String city;

    private Double padelLevel;

}
