package com.mls.service.padel_match.mapper;

import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.dto.response.MatchPlayer;
import com.mls.service.padel_match.dto.response.PadelMatchDTO;
import com.mls.service.padel_match.model.PadelMatchEntity;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public PadelMatchDTO padelMatchEntityToPadelMatchDTO(PadelMatchEntity matchEntity, List<MatchPlayer> players) {
        return PadelMatchDTO.builder()
                .id(matchEntity.getId())
                .matchDateStart(matchEntity.getMatchDateStart())
                .matchDateEnd(matchEntity.getMatchDateEnd())
                .matchLevelStart(matchEntity.getMatchLevelStart())
                .matchLevelEnd(matchEntity.getMatchLevelEnd())
                .durationInMinutes(matchEntity.getDurationInMinutes())
                .pricePerPerson(matchEntity.getPricePerPerson())
                .padelCourtId(matchEntity.getPadelCourtId())
                .scoreA(matchEntity.getScoreA())
                .scoreB(matchEntity.getScoreB())
                .isResultValidated(matchEntity.getIsResultValidated())
                .winner(matchEntity.getWinner())
                .isCompetitive(matchEntity.isCompetitive())
                .isPrivate(matchEntity.isPrivate())
                .players(players)
                .build();
    }
}
