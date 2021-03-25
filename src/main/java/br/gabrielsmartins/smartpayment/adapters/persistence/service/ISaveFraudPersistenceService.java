package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudEntity;
import reactor.core.publisher.Mono;

public interface ISaveFraudPersistenceService {

    Mono<FraudEntity> save(FraudEntity fraud);
}
