package br.gabrielsmartins.smartpayment.frauds.application.ports.in;

import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import reactor.core.publisher.Mono;

public interface SaveFraudUseCase {

    Mono<FraudAnalysis> save(FraudAnalysis order);

}
