package br.gabrielsmartins.smartpayment.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.adapters.persistence.service.SaveFraudPersistenceService;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.ports.out.SaveFraudPort;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.FraudPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaveFraudPersistenceAdapter implements SaveFraudPort {

    private final SaveFraudPersistenceService service;
    private final FraudPersistenceMapper mapper;

    @Override
    public Mono<FraudAnalysis> save(FraudAnalysis fraudAnalysis) {
        return service.save(mapper.mapToEntity(fraudAnalysis))
                      .map(mapper::mapToDomain);
    }

}
