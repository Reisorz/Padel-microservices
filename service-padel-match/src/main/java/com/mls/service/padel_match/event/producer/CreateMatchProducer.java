package com.mls.service.padel_match.event.producer;
import com.mls.service.padel_match.event.CreateMatchEvent;
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

    private final String topic = "create-match";

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