package com.mls.service.user.dto.request;

import com.mls.service.user.model.PreferredSide;
import com.mls.service.user.model.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data

public class UserUpdateRequest {

    private String name;
    private String city;
    private Double padelLevel;
    private PreferredSide preferredSide;


}

