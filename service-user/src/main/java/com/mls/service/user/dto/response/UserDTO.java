package com.mls.service.user.dto.response;

import com.mls.service.user.model.PreferredSide;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String city;

    private Double padelLevel;

    private PreferredSide preferredSide;

    private String avatarImageUrl;

}
