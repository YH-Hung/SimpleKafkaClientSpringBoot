package hle.kafka.simplerestclient.background;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class StreamProcessor {

    @Autowired
    void NormalInnerJoin(StreamsBuilder streamsBuilder) {
        KStream<String, Integer> pdStream = streamsBuilder.stream("pd-scores", Consumed.with(Serdes.String(), Serdes.Integer()));
        KStream<String, Integer> shieldStream = streamsBuilder.stream("shield-scores", Consumed.with(Serdes.String(), Serdes.Integer()));

        KStream<String, Integer> integratedScoreStream = pdStream.join(shieldStream, Integer::sum,
                JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofMinutes(5)), StreamJoined.with(Serdes.String(), Serdes.Integer(), Serdes.Integer()));

        integratedScoreStream.to("quickstart-events", Produced.with(Serdes.String(), Serdes.Integer()));
    }
}
