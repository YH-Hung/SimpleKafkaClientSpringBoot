package hle.kafka.simplerestclient.background;

import hle.kafka.simplerestclient.dto.ScoreResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NetScoreChecker {

    @KafkaListener(topics="test-serde-bytearray")
    public void printByteArrayTopicMessage(ScoreResult message) {
        log.info("Score check from byte[]: {}", message);
    }

    @KafkaListener(topics="test-serde-string")
    public void printStringTopicMessage(ScoreResult message) {
        log.info("Score check from string: {}", message);
    }
}
