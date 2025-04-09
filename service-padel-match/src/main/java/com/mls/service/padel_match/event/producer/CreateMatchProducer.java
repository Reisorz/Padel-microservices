package com.mls.service.padel_match.event.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.event.CreateMatchEvent;
import com.mls.service.padel_match.model.PadelMatchEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CreateMatchProducer {

    private final KafkaTemplate<String, CreateMatchEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.create-match}")
    private String topic;

    public CreateMatchProducer(KafkaTemplate<String, CreateMatchEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCreateMatchEvent(List<Long> playersIds, Long matchId) {
        CreateMatchEvent createMatchEvent = new CreateMatchEvent();
        createMatchEvent.setCreatedAt(LocalDateTime.now());
        createMatchEvent.setMatchId(matchId);
        createMatchEvent.setPlayersIds(playersIds);

        kafkaTemplate.send(topic, createMatchEvent);
        log.info("Sent createMatchEvent to topic: {}", topic);
    }



}