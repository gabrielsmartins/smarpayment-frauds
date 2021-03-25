package br.gabrielsmartins.smartpayment.application.ports.in;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import reactor.core.publisher.Mono;

public interface SaveFraudUseCase {

    Mono<FraudAnalysis> save(FraudAnalysis order);

}
