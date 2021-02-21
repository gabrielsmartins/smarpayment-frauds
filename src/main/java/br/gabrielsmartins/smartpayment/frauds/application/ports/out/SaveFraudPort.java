package br.gabrielsmartins.smartpayment.frauds.application.ports.out;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import reactor.core.publisher.Mono;

public interface SaveFraudPort {

    Mono<Fraud> save(Fraud fraud);

}
