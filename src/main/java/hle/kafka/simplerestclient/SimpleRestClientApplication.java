package hle.kafka.simplerestclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafkaStreams
@SpringBootApplication
public class SimpleRestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestClientApplication.class, args);
    }

}
