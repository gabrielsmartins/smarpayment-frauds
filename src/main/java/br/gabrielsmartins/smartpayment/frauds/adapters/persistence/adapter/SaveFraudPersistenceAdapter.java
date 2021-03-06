package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper.FraudPersistenceMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service.SaveFraudPersistenceService;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.SaveFraudPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaveFraudPersistenceAdapter implements SaveFraudPort {

    private final SaveFraudPersistenceService service;
    private final FraudPersistenceMapper mapper;

    @Override
    public Mono<Fraud> save(Fraud fraud) {
        return service.save(mapper.mapToEntity(fraud))
                      .map(mapper::mapToDomain);
    }

}
