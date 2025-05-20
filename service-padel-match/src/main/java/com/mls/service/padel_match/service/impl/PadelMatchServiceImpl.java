package com.mls.service.padel_match.service.impl;

import com.mls.service.padel_match.client.MatchUserClient;
import com.mls.service.padel_match.client.PadelCourtClient;
import com.mls.service.padel_match.client.UserClient;
import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.dto.response.*;
import com.mls.service.padel_match.event.producer.CreateMatchProducer;
import com.mls.service.padel_match.mapper.PadelMatchMapper;
import com.mls.service.padel_match.model.PadelMatchEntity;
import com.mls.service.padel_match.repository.PadelMatchRepository;
import com.mls.service.padel_match.service.PadelMatchService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PadelMatchServiceImpl implements PadelMatchService {

    @Autowired
    private PadelMatchRepository matchRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private PadelCourtClient padelCourtClient;

    @Autowired
    private PadelMatchMapper mapper;

    @Autowired
    private MatchUserClient matchUserClient;

    @Autowired
    private CreateMatchProducer createMatchProducer;

    @Override
    public List<PadelMatchEntity> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    @Transactional
    public PadelMatchEntity createPadelMatch(CreateMatchRequest request) {
        //Check if court exists asking msvc-padel-court
        padelCourtClient.getPadelCourtById(request.getPadelCourtId());

        //Check if users exist
        List<Long> players = new ArrayList<>();
        players.addAll(request.getTeamA());
        players.addAll(request.getTeamB());
        for(Long player: players){
            userClient.getUserById(player);
        }

        PadelMatchEntity match = mapper.createMatchRequestToPadelMatchEntity(request);
        PadelMatchEntity savedMatch = matchRepository.save(match);

        //Add users to match
        boolean isOrganizer = false;
        List<Long> teamA = request.getTeamA();
        for(Long player: teamA){
            isOrganizer = Objects.equals(player, request.getOrganizer());
            matchUserClient.addUserToMatch(player,savedMatch.getId(),"A", isOrganizer);
        }

        List<Long> teamB = request.getTeamB();
        for(Long player: teamB){
            isOrganizer = Objects.equals(player, request.getOrganizer());
            matchUserClient.addUserToMatch(player,savedMatch.getId(),"B", isOrganizer);
        }

        //Kafka
        teamA.addAll(teamB);
        createMatchProducer.sendCreateMatchEvent(teamA, savedMatch.getId());

        return savedMatch;
    }

    @Override
    public PadelMatchEntity getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Match with id " + id + " not found"));
    }

    @Override
    public void deleteMatch(Long matchId) {
        PadelMatchEntity match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match with id " + matchId + " not found"));

        matchUserClient.deleteAllUsersFromMatch(matchId);

        matchRepository.deleteById(matchId);
    }

    @Override
    public List<PadelMatchDTO> getAllMatchesAndPlayers() {
        List<PadelMatchEntity> matchEntities = matchRepository.findAll();
        List<PadelMatchDTO> padelMatchDTOS = new ArrayList<>();

        for (PadelMatchEntity matchEntity : matchEntities) {
            List<MatchUserDTO> matchUserDTOS = matchUserClient.getAllUsersFromMatch(matchEntity.getId());

            List<Long> userIds = new ArrayList<>();
            for (MatchUserDTO matchUserDTO : matchUserDTOS) {
                userIds.add(matchUserDTO.getUserId());
            }
            List<UserDTO> userDTOS = userClient.getAllUsersByIds(userIds);

            Map<Long, MatchUserDTO> matchUserMap = matchUserDTOS.stream()
                    .collect(Collectors.toMap(MatchUserDTO::getUserId, Function.identity()));

            List<MatchPlayer> players = new ArrayList<>();
            for (UserDTO user : userDTOS) {
                MatchUserDTO matchUserInfo = matchUserMap.get(user.getId());
                if (matchUserInfo != null) {
                    MatchPlayer matchPlayer = MatchPlayer.builder()
                            .userId(user.getId())
                            .name(user.getName())
                            .padelLevel(user.getPadelLevel())
                            .team(matchUserInfo.getTeam())
                            .isOrganizer(matchUserInfo.isOrganizer())
                            .avatarImageUrl(user.getAvatarImageUrl())
                            .build();
                    players.add(matchPlayer);
                }
            }

            PadelCourtDTO court = padelCourtClient.getPadelCourtById(matchEntity.getPadelCourtId());

            PadelMatchDTO padelMatchDTO = mapper.padelMatchEntityToPadelMatchDTO(matchEntity, players, court);
            padelMatchDTOS.add(padelMatchDTO);
        }
        return padelMatchDTOS;
    }

}
