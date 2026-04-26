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
import com.mls.service.padel_match.specifications.PadelMatchEntitySpecification;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
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
        List<CreateMatchRequest.PlayerSlot> players = new ArrayList<>(request.getPlayers());
        for(CreateMatchRequest.PlayerSlot player: players){
            userClient.getUserById(player.getUserId());
        }

        PadelMatchEntity match = mapper.createMatchRequestToPadelMatchEntity(request);
        PadelMatchEntity savedMatch = matchRepository.save(match);

        //Add users to match
        for (CreateMatchRequest.PlayerSlot player : request.getPlayers()) {
            boolean isOrganizer = Objects.equals(player.getUserId(), request.getOrganizer());

            MatchUserDTO matchUserDTO = MatchUserDTO.builder()
                    .matchId(savedMatch.getId())
                    .userId(player.getUserId())
                    .slot(player.getSlot())
                    .isOrganizer(isOrganizer)
                    .build();
            matchUserClient.addUserToMatch(matchUserDTO);
        }

        //Kafka
        List<Long> playerIds = request.getPlayers().stream()
                .map(CreateMatchRequest.PlayerSlot::getUserId)
                .collect(Collectors.toList());
        createMatchProducer.sendCreateMatchEvent(playerIds, savedMatch.getId());

        return savedMatch;
    }

    @Override
    public PadelMatchEntity getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Match with id " + id + " not found"));
    }

    @Override
    @Transactional
    public void deleteMatch(Long matchId) {
        PadelMatchEntity match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match with id " + matchId + " not found"));

        matchUserClient.deleteAllUsersFromMatch(matchId);

        matchRepository.deleteById(matchId);
    }

    @Override
    public List<PadelMatchDTO> getMatchesAndPlayersWithSpecifications(List<LocalDateTime> dates, Double userPadelLevel) {

        //Specifications:
        // - Matches with startMatchDate coincident with provided dates.
        // - Matches where userPadelLevel is between matchLevelStart & matchLevelEnd.
        Specification<PadelMatchEntity> specDates = PadelMatchEntitySpecification.datesIn(dates);
        Specification<PadelMatchEntity> specUserPadelLevel = PadelMatchEntitySpecification.userPadelLevelBetweenMatchRange(userPadelLevel);
        Specification<PadelMatchEntity> specFinal = Specification.where(specDates).and(specUserPadelLevel);

        List<PadelMatchEntity> matchEntities = matchRepository.findAll(specFinal);
        List<PadelMatchDTO> padelMatchDTOS = new ArrayList<>();

        // For each match...
        for (PadelMatchEntity matchEntity : matchEntities) {
            List<MatchUserDTO> matchUserDTOS = matchUserClient.getAllUsersFromMatch(matchEntity.getId());

            // Get every player info and make a List
            List<Long> userIds = new ArrayList<>();
            for (MatchUserDTO matchUserDTO : matchUserDTOS) {
                userIds.add(matchUserDTO.getUserId());
            }
            List<UserDTO> userDTOS = userClient.getAllUsersByIds(userIds);

            // Then create Map for faster search
            Map<Long, MatchUserDTO> matchUserMap = matchUserDTOS.stream()
                    .collect(Collectors.toMap(MatchUserDTO::getUserId, Function.identity()));

            // Then combine the info from matchUserMap & userDTOS into a List of MatchPlayers
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
                            .slot(matchUserInfo.getSlot())
                            .avatarImageUrl(user.getAvatarImageUrl())
                            .build();
                    players.add(matchPlayer);
                }
            }
            players.sort(Comparator.comparingInt(MatchPlayer::getSlot));

            PadelCourtDTO court = padelCourtClient.getPadelCourtById(matchEntity.getPadelCourtId());
            PadelMatchDTO padelMatchDTO = mapper.padelMatchEntityToPadelMatchDTO(matchEntity, players, court);
            padelMatchDTOS.add(padelMatchDTO);
        }
        return padelMatchDTOS;
    }
}
