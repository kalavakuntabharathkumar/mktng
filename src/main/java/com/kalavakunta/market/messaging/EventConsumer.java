package com.kalavakunta.market.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalavakunta.market.model.MarketEvent;
import com.kalavakunta.market.persistence.StoredEvent;
import com.kalavakunta.market.persistence.StoredEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
    private final StoredEventRepository repository;
    private final ObjectMapper mapper;
    public EventConsumer(StoredEventRepository repository, ObjectMapper mapper) {
        this.repository = repository; this.mapper = mapper;
    }
    @KafkaListener(topics = EventPublisher.TOPIC, groupId = "market-ingestion")
    public void consume(MarketEvent event) throws JsonProcessingException {
        repository.save(new StoredEvent(event.source(), event.symbol(), event.eventType(),
            mapper.writeValueAsString(event.payload()), event.eventTime()));
    }
}
