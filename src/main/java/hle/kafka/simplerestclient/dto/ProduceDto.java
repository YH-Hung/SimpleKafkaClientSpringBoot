package hle.kafka.simplerestclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduceDto {
    String topicName;
    String keyForPartition;
    String messageContent;
}
