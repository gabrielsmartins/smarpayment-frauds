package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.FraudItemRepository;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.FraudRepository;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.PaymentMethodRepository;
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
    private final FraudItemRepository fraudItemRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public Mono<FraudEntity> findById(UUID id) {
        return this.repository.findById(id)
                              .flatMap(this::addItems)
                              .flatMap(this::addPaymentMethods);
    }

    @Override
    public Flux<FraudEntity> findAll() {
        return this.repository.findAll()
                   .flatMap(this::addItems)
                   .flatMap(this::addPaymentMethods);
    }

    @Override
    public Mono<FraudEntity> findByOrderId(Long orderId) {
        return this.repository.findByOrderId(orderId)
                              .flatMap(this::addItems)
                              .flatMap(this::addPaymentMethods);
    }

    @Override
    public Flux<FraudEntity> findByProductId(UUID productId, Pageable pageable) {
        return this.repository.findByProductId(productId, pageable)
                              .flatMap(fraudEntity -> fraudItemRepository.findByFraudIdAndProductId(fraudEntity.getId(), productId)
                                                                         .flatMap(item -> {
                                                                             fraudEntity.addItem(item);
                                                                             return Mono.just(fraudEntity);
                                                                         }))
                              .flatMap(this::addPaymentMethods);
    }

    @Override
    public Flux<FraudEntity> findByCustomerId(UUID customerId, Pageable pageable) {
        return this.repository.findByCustomerId(customerId, pageable)
                              .flatMap(this::addItems)
                              .flatMap(this::addPaymentMethods);
    }

    @Override
    public Flux<FraudEntity> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable) {
        return this.repository.findByInterval(startDatetime, endDatetime, pageable)
                              .flatMap(this::addItems)
                              .flatMap(this::addPaymentMethods);
    }

    public Mono<FraudEntity> addItems(FraudEntity fraudEntity){
        fraudItemRepository.findByFraudId(fraudEntity.getId())
                           .map(fraudEntity::addItem);
        return Mono.just(fraudEntity);
    }

    public Mono<FraudEntity> addPaymentMethods(FraudEntity fraudEntity){
        paymentMethodRepository.findByFraudId(fraudEntity.getId())
                               .map(fraudEntity::addPaymentMethod);
        return Mono.just(fraudEntity);
    }
}
