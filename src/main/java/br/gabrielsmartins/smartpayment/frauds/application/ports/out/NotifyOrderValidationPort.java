package br.gabrielsmartins.smartpayment.frauds.application.ports.out;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import reactor.core.publisher.Mono;

public interface NotifyOrderValidationPort {

    Mono<Order> notify(Order order);
}
