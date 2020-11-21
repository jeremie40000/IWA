package jha.stopcovid.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic topicGeoloc() {
        return TopicBuilder.name("topic-geoloc")
                .config(TopicConfig.RETENTION_MS_CONFIG, "180000").build();
    }

    @Bean
    public NewTopic topicSuspicious() {
        return TopicBuilder.name("topic-suspicious")
                .config(TopicConfig.RETENTION_MS_CONFIG, "900000").build();
    }
}
