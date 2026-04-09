package com.mls.service.user.dto.response;

import com.mls.service.user.model.PreferredSide;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String city;

    private Double padelLevel;

    private PreferredSide preferredSide;

    private String avatarImageUrl;


}
