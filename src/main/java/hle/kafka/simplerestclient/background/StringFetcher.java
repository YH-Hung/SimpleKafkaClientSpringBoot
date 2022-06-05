package hle.kafka.simplerestclient.background;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringFetcher {

    @KafkaListener(topics="quickstart-events")
    public void printTopicMessage(String message) {
        log.info(message);
    }
}
