package com.mls.service.padel_match.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMatchEvent {
    private Long matchId;
    private List<Long> playersIds;
    private LocalDateTime createdAt;
}
