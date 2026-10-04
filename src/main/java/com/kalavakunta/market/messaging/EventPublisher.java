package com.kalavakunta.market.messaging;

import com.kalavakunta.market.model.MarketEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    public static final String TOPIC = "market-events";
    private final KafkaTemplate<String, MarketEvent> kafkaTemplate;
    public EventPublisher(KafkaTemplate<String, MarketEvent> kafkaTemplate) { this.kafkaTemplate = kafkaTemplate; }
    public void publish(MarketEvent event) {
        kafkaTemplate.send(TOPIC, event.symbol(), event);
    }
}
