package com.mls.service.padel_match.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchPlayer {
    private Long userId;
    private String name;
    private Double padelLevel;
    private boolean isOrganizer;
    private String avatarImageUrl;

    @Enumerated(EnumType.STRING)
    private MatchUserDTO.Team team;

}


