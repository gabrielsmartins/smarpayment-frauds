package br.gabrielsmartins.smartpayment.frauds.application.ports.in;

import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SearchFraudUseCase {

    Mono<FraudAnalysis> findById(String id);

    Flux<FraudAnalysis> findAll();

    Mono<FraudAnalysis> findByOrderId(Long orderId);

    Flux<FraudAnalysis> findByProductId(UUID productId, Pageable pageable);

    Flux<FraudAnalysis> findByCustomerId(UUID customerId, Pageable pageable);

    Flux<FraudAnalysis> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable);
}
