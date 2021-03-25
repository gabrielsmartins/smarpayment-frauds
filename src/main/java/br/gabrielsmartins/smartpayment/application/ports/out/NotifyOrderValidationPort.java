package br.gabrielsmartins.smartpayment.application.ports.out;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import reactor.core.publisher.Mono;

public interface NotifyOrderValidationPort {

    Mono<FraudAnalysis> notify(FraudAnalysis fraudAnalysis);

}
