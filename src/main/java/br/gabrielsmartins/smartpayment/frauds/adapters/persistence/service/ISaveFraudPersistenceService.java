package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import reactor.core.publisher.Mono;

public interface ISaveFraudPersistenceService {

    Mono<FraudEntity> save(FraudEntity fraud);
}
