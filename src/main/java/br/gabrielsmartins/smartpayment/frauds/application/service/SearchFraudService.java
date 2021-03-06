package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.SearchFraudUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.SearchFraudPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchFraudService implements SearchFraudUseCase {

    private final SearchFraudPort port;

    @Override
    public Mono<Fraud> findById(UUID id) {
        return this.port.findById(id);
    }

    @Override
    public Flux<Fraud> findAll() {
        return this.port.findAll();
    }

    @Override
    public Mono<Fraud> findByOrderId(Long orderId) {
        return this.port.findByOrderId(orderId);
    }

    @Override
    public Flux<Fraud> findByProductId(UUID productId, Pageable pageable) {
        return this.port.findByProductId(productId, pageable);
    }

    @Override
    public Flux<Fraud> findByCustomerId(UUID customerId, Pageable pageable) {
        return this.port.findByCustomerId(customerId, pageable);
    }

    @Override
    public Flux<Fraud> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable) {
        return this.port.findByInterval(startDatetime, endDatetime, pageable);
    }
}
