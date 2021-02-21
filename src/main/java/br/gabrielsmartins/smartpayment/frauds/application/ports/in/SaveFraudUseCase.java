package br.gabrielsmartins.smartpayment.frauds.application.ports.in;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import reactor.core.publisher.Mono;

public interface SaveFraudUseCase {

    Mono<Fraud> save(Fraud order);

}
