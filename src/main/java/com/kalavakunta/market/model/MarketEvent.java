package com.kalavakunta.market.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Map;

public record MarketEvent(
    @NotBlank String source,
    @NotBlank String symbol,
    @NotBlank String eventType,
    @NotNull Map<String, Object> payload,
    Instant eventTime
) {
    public MarketEvent {
        if (eventTime == null) eventTime = Instant.now();
    }
}
