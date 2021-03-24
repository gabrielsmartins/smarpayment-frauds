package br.gabrielsmartins.smartpayment.frauds.application.ports.out;

import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import reactor.core.publisher.Mono;

public interface SaveFraudPort {

    Mono<FraudAnalysis> save(FraudAnalysis fraudAnalysis);

}
