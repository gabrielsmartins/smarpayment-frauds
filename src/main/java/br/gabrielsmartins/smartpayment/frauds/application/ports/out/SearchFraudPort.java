package br.gabrielsmartins.smartpayment.frauds.application.ports.out;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SearchFraudPort {

    Mono<Fraud> findById(String id);

    Flux<Fraud> findAll();

    Mono<Fraud> findByOrderId(Long orderId);

    Flux<Fraud> findByProductId(UUID productId, Pageable pageable);

    Flux<Fraud> findByCustomerId(UUID customerId, Pageable pageable);

    Flux<Fraud> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable);
}
