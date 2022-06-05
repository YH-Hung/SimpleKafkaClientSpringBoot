package hle.kafka.simplerestclient.controllers;

import hle.kafka.simplerestclient.dto.ProduceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/produce")
public class ProduceController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public String postObj(@RequestBody ProduceDto model){
        String backMsg = "";
        try {
            var sr = kafkaTemplate.send(model.getTopicName(), model.getKeyForPartition(), model.getMessageContent()).get();
            return sr.toString();
        } catch (ExecutionException e) {
            return e.toString();
        } catch (InterruptedException e) {
            return e.toString();
        }

    }
}
