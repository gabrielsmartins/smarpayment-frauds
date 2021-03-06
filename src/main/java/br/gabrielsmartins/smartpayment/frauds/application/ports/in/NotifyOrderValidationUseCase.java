package br.gabrielsmartins.smartpayment.frauds.application.ports.in;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import reactor.core.publisher.Mono;

public interface NotifyOrderValidationUseCase {
    
    Mono<Order> notify(Order order);

}
