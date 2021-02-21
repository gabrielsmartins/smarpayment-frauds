package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.FraudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchFraudPersistenceService implements ISearchFraudPersistenceService{

    private final FraudRepository repository;

    @Override
    public Mono<FraudEntity> findById(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public Flux<FraudEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Mono<FraudEntity> findByOrderId(Long orderId) {
        return this.repository.findByOrderId(orderId);
    }

    @Override
    public Flux<FraudEntity> findByProductId(UUID productId, Pageable pageable) {
        return this.repository.findByProductId(productId, pageable);
    }

    @Override
    public Flux<FraudEntity> findByCustomerId(UUID customerId, Pageable pageable) {
        return this.repository.findByCustomerId(customerId, pageable);
    }

    @Override
    public Flux<FraudEntity> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable) {
        return this.repository.findByInterval(startDatetime, endDatetime, pageable);
    }
}
