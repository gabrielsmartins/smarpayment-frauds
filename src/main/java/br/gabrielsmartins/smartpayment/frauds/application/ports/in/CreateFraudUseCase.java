package br.gabrielsmartins.smartpayment.frauds.application.ports.in;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import reactor.core.publisher.Mono;

public interface CreateFraudUseCase {

    Mono<Fraud> create(Order order);
}
