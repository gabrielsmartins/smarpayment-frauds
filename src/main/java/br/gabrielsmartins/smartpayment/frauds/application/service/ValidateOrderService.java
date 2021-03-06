package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.ValidateOrderUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ValidateOrderService implements ValidateOrderUseCase {

    @Override
    public Mono<Boolean> isValid(Order order) {
        log.info("Validating order: {}", order);
        return Mono.just(order.getTotalDiscount().compareTo(order.getTotalAmount()) > 0);
    }

}
