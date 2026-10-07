package com.kalavakunta.market.persistence;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "market_events", indexes = {
    @Index(name = "idx_market_symbol_time", columnList = "symbol,eventTime")
})
public class StoredEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String source;
    @Column(nullable = false) private String symbol;
    @Column(nullable = false) private String eventType;
    @Column(nullable = false, length = 12000) private String payloadJson;
    @Column(nullable = false) private Instant eventTime;
    @Column(nullable = false) private Instant ingestedAt = Instant.now();

    protected StoredEvent() {}
    public StoredEvent(String source, String symbol, String eventType, String payloadJson, Instant eventTime) {
        this.source = source; this.symbol = symbol; this.eventType = eventType;
        this.payloadJson = payloadJson; this.eventTime = eventTime;
    }
    public Long getId() { return id; }
    public String getSource() { return source; }
    public String getSymbol() { return symbol; }
    public String getEventType() { return eventType; }
    public String getPayloadJson() { return payloadJson; }
    public Instant getEventTime() { return eventTime; }
    public Instant getIngestedAt() { return ingestedAt; }
}
