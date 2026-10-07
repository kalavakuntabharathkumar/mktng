package com.kalavakunta.market.model;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MarketEventTest {
    @Test void fillsMissingEventTime() {
        MarketEvent event = new MarketEvent("feed", "MSFT", "QUOTE", Map.of("price", 410), null);
        assertNotNull(event.eventTime());
        assertEquals("MSFT", event.symbol());
    }
}
