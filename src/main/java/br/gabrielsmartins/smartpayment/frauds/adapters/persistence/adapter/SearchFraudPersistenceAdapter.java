package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper.FraudPersistenceMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service.SearchFraudPersistenceService;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.SearchFraudPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SearchFraudPersistenceAdapter implements SearchFraudPort {

    private final SearchFraudPersistenceService service;
    private final FraudPersistenceMapper mapper;

    @Override
    public Mono<Fraud> findById(String id) {
        return this.service.findById(id)
                           .map(mapper::mapToDomain);
    }

    @Override
    public Flux<Fraud> findAll() {
        return this.service.findAll()
                           .map(mapper::mapToDomain);
    }

    @Override
    public Mono<Fraud> findByOrderId(Long orderId) {
        return this.service.findByOrderId(orderId)
                           .map(mapper::mapToDomain);
    }

    @Override
    public Flux<Fraud> findByProductId(UUID productId, Pageable pageable) {
        return this.service.findByProductId(productId, pageable)
                           .map(mapper::mapToDomain);
    }

    @Override
    public Flux<Fraud> findByCustomerId(UUID customerId, Pageable pageable) {
        return this.service.findByCustomerId(customerId, pageable)
                           .map(mapper::mapToDomain);
    }

    @Override
    public Flux<Fraud> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable) {
        return this.service.findByInterval(startDatetime, endDatetime, pageable)
                           .map(mapper::mapToDomain);
    }
}
