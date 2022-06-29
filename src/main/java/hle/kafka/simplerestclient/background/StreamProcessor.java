package hle.kafka.simplerestclient.background;

import hle.kafka.simplerestclient.dto.RatingResult;
import hle.kafka.simplerestclient.dto.ScoreResult;
import hle.kafka.simplerestclient.service.RatingService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class StreamProcessor {

    private final RatingService ratingService;
    private final JsonSerde<ScoreResult> scoreResultJsonSerde;
    private final JsonSerde<RatingResult> ratingResultJsonSerde;

    public StreamProcessor(RatingService ratingService, JsonSerde<ScoreResult> scoreResultJsonSerde, JsonSerde<RatingResult> ratingResultJsonSerde) {
        this.ratingService = ratingService;
        this.scoreResultJsonSerde = scoreResultJsonSerde;
        this.ratingResultJsonSerde = ratingResultJsonSerde;
    }

    @Autowired
    void normalInnerJoin(StreamsBuilder streamsBuilder) {
        KStream<String, ScoreResult> pdStream = streamsBuilder.stream("pd-scores", Consumed.with(Serdes.String(), scoreResultJsonSerde));
        KStream<String, ScoreResult> shieldStream = streamsBuilder.stream("shield-scores", Consumed.with(Serdes.String(), scoreResultJsonSerde));

        KStream<String, RatingResult> integratedScoreStream = pdStream.join(shieldStream, ratingService::integrateRate,
                JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofMinutes(1)), StreamJoined.with(Serdes.String(), scoreResultJsonSerde, scoreResultJsonSerde));

        integratedScoreStream.to("quickstart-events", Produced.with(Serdes.String(), ratingResultJsonSerde));
    }

}
