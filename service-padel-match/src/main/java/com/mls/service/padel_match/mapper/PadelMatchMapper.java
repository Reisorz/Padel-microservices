package com.mls.service.padel_match.mapper;

import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.model.PadelMatchEntity;
import org.springframework.stereotype.Component;

@Component
public class PadelMatchMapper {

    public PadelMatchEntity createMatchRequestToPadelMatchEntity(CreateMatchRequest request) {
        PadelMatchEntity match = PadelMatchEntity.builder()
                .matchDateStart(request.getMatchDateStart())
                .padelCourtId(request.getPadelCourtId())
                .durationInMinutes(request.getDurationInMinutes())
                .organizer(request.getOrganizer())
                .isPrivate(request.isPrivate())
                .isCompetitive(request.isCompetitive())
                .matchDateEnd(request.getMatchDateEnd())
                .matchLevelStart(request.getMatchLevelStart())
                .matchLevelEnd(request.getMatchLevelEnd())
                .teamA(request.getTeamA())
                .teamB(request.getTeamB())
                .pricePerPerson(request.getPricePerPerson())
                .build();
        return match;
    }
}
