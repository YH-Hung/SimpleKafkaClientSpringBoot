package hle.kafka.simplerestclient.controllers;

import hle.kafka.simplerestclient.dto.ProduceDto;
import hle.kafka.simplerestclient.dto.ScoreResult;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/produce")
public class ProduceController {

    private final KafkaTemplate<String, ScoreResult> kafkaTemplate;

    public ProduceController(KafkaTemplate<String, ScoreResult> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String postObj(@RequestBody ProduceDto model){
        try {
            var sr = kafkaTemplate.send(model.getTopicName(), model.getKeyForPartition(), model.getScore()).get();
            return sr.toString();
        } catch (ExecutionException e) {
            return e + "Execution Exception";
        } catch (InterruptedException e) {
            return e + "Interrupted Exception";
        }

    }
}
