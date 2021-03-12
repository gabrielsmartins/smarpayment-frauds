package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;


import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface FraudRepository extends ReactiveMongoRepository<FraudEntity, String> {

    Mono<FraudEntity> findByOrderId(Long orderId);

    @Query(value = "{ 'items.productId' : $0 }")
    Flux<FraudEntity> findByProductId(UUID productId, Pageable pageable);

    Flux<FraudEntity> findByCustomerId(UUID customerId, Pageable pageable);

    @Query(value = "{ 'createdAt' : { $gte: ?0, $lt: ?1 } }")
    Flux<FraudEntity> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable);

}
