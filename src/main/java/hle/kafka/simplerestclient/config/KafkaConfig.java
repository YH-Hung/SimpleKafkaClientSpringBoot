package hle.kafka.simplerestclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;

@Configuration
public class KafkaConfig {

    /**
     * Boot will autowire this into the container factory.
     */
    @Bean
    public CommonLoggingErrorHandler errorHandler() {
        return new CommonLoggingErrorHandler();
    }
}
