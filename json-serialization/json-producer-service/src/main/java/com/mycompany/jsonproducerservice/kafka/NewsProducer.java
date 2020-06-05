package com.mycompany.jsonproducerservice.kafka;

import com.mycompany.jsonproducerservice.domain.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class NewsProducer {

    private final KafkaTemplate<String, News> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void send(News news) {
        String kafkaTopic = kafkaProperties.getProducer().getProperties().get("topic");
        log.info("Sending News '{}' to topic '{}'", news, kafkaTopic);
        kafkaTemplate.send(kafkaTopic, news.getId(), news);
    }

}
