package com.mls.service.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchUserDTO {

    private Long id;
    private Long matchId;
    private Long userId;
    private boolean isOrganizer;
    private Team team;

    public enum Team {
        A,
        B
    }

}