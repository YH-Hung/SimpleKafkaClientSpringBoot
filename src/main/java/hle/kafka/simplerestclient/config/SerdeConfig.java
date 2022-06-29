package hle.kafka.simplerestclient.config;

import hle.kafka.simplerestclient.dto.RatingResult;
import hle.kafka.simplerestclient.dto.ScoreResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class SerdeConfig {

    @Bean
    public JsonSerde<ScoreResult> scoreResultJsonSerde(){
        return new JsonSerde<>(ScoreResult.class);
    }

    @Bean
    public JsonSerde<RatingResult> ratingResultJsonSerde() {
        return new JsonSerde<>(RatingResult.class);
    }
}
