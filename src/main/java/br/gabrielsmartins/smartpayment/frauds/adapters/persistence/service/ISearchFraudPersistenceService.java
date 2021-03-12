package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ISearchFraudPersistenceService {

    Mono<FraudEntity> findById(String id);

    Flux<FraudEntity> findAll();

    Mono<FraudEntity> findByOrderId(Long orderId);

    Flux<FraudEntity> findByProductId(UUID productId, Pageable pageable);

    Flux<FraudEntity> findByCustomerId(UUID customerId, Pageable pageable);

    Flux<FraudEntity> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable);
}
