package com.kalavakunta.market.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoredEventRepository extends JpaRepository<StoredEvent, Long> {
    List<StoredEvent> findTop100ByOrderByIngestedAtDesc();
    List<StoredEvent> findTop100BySymbolOrderByIngestedAtDesc(String symbol);
}
