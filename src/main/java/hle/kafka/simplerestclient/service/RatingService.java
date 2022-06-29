package hle.kafka.simplerestclient.service;

import hle.kafka.simplerestclient.dto.RatingResult;
import hle.kafka.simplerestclient.dto.ScoreResult;
import hle.kafka.simplerestclient.model.ScoreRating;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    public RatingResult integrateRate(ScoreResult pdScore, ScoreResult shieldScore) {
        var result = new RatingResult();
        result.setName(pdScore.getName());
        int mergedScore = pdScore.getScore() + shieldScore.getScore();
        result.setMergedScore(mergedScore);
        result.setLevel(getRating(mergedScore));

        return result;
    }

    private ScoreRating getRating(Integer mergedScore){
        if (mergedScore > 800) {
            return ScoreRating.GOOD;
        } else if (mergedScore > 500) {
            return ScoreRating.MEDIUM;
        } else {
            return ScoreRating.BAD;
        }
    }
}
