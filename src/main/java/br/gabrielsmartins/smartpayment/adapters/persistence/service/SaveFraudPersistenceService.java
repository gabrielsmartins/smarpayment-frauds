package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.FraudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SaveFraudPersistenceService implements ISaveFraudPersistenceService {

    private final FraudRepository fraudRepository;

    @Override
    public Mono<FraudEntity> save(FraudEntity fraud) {
        return this.fraudRepository.save(fraud);
    }
}
