package com.kalavakunta.market.api;

import com.kalavakunta.market.messaging.EventPublisher;
import com.kalavakunta.market.model.MarketEvent;
import com.kalavakunta.market.persistence.StoredEvent;
import com.kalavakunta.market.persistence.StoredEventRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventPublisher publisher;
    private final StoredEventRepository repository;
    public EventController(EventPublisher publisher, StoredEventRepository repository) {
        this.publisher = publisher; this.repository = repository;
    }
    @PostMapping
    public ResponseEntity<Map<String, String>> ingest(@Valid @RequestBody MarketEvent event) {
        publisher.publish(event);
        return ResponseEntity.accepted().body(Map.of("status", "accepted", "symbol", event.symbol()));
    }
    @GetMapping
    public List<StoredEvent> list(@RequestParam(required = false) String symbol) {
        return (symbol == null || symbol.isBlank())
            ? repository.findTop100ByOrderByIngestedAtDesc()
            : repository.findTop100BySymbolOrderByIngestedAtDesc(symbol);
    }
}
