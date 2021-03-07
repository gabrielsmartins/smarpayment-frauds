package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.FraudItemRepository;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.FraudRepository;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SaveFraudPersistenceService implements ISaveFraudPersistenceService {

    private final FraudRepository fraudRepository;
    private final FraudItemRepository fraudItemRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    @Transactional
    public Mono<FraudEntity> save(FraudEntity fraud) {
        return this.fraudRepository.save(fraud)
                                   .then(fraudItemRepository.saveAll(fraud.getItems()).then())
                                   .then(paymentMethodRepository.saveAll(fraud.getPaymentMethods()).then())
                                   .thenReturn(fraud);
    }
}
