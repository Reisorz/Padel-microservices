package com.mls.service.notification.event.consumer;
import com.mls.service.notification.event.CreateMatchEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateMatchConsumer {

    @KafkaListener(topics = "create-match", groupId = "padel-match-group")
    public void createMatchListener(CreateMatchEvent event) {
        List<Long> players = event.getPlayersIds();
        for (Long playerId : players) {
            System.out.println("Player " + playerId + " has been added to match " + event.getMatchId());
            //MORE LOGIC HERE
        }
    }
}
