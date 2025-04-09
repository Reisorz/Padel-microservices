package com.mls.service.padel_match.mapper;

import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.event.CreateMatchEvent;
import com.mls.service.padel_match.model.PadelMatchEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PadelMatchMapper {

    public PadelMatchEntity createMatchRequestToPadelMatchEntity(CreateMatchRequest request) {
        PadelMatchEntity match = PadelMatchEntity.builder()
                .matchDateStart(request.getMatchDateStart())
                .padelCourtId(request.getPadelCourtId())
                .durationInMinutes(request.getDurationInMinutes())
                .isPrivate(request.isPrivate())
                .isCompetitive(request.isCompetitive())
                .matchDateEnd(request.getMatchDateEnd())
                .matchLevelStart(request.getMatchLevelStart())
                .matchLevelEnd(request.getMatchLevelEnd())
                .pricePerPerson(request.getPricePerPerson())
                .build();
        return match;
    }
}
