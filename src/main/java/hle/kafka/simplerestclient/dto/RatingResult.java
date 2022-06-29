package hle.kafka.simplerestclient.dto;

import hle.kafka.simplerestclient.model.ScoreRating;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingResult {
    String name;
    Integer mergedScore;
    ScoreRating level;
}
