package hle.kafka.simplerestclient.background;

import hle.kafka.simplerestclient.dto.RatingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RatingFetcher {

    @KafkaListener(topics="quickstart-events")
    public void printTopicMessage(RatingResult message) {
        log.info("Integrated Score: {}", message);
    }
}
