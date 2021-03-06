package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.CreateFraudUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.NotifyOrderValidationUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.ProcessOrderUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.ValidateOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessOrderService implements ProcessOrderUseCase {

    private final ValidateOrderUseCase validateOrderUseCase;
    private final CreateFraudUseCase createFraudUseCase;
    private final NotifyOrderValidationUseCase notifyOrderValidationUseCase;

    @Override
    public Mono<Order> process(Order order) {
        log.info("Processing order: {}", order);
        return validateOrderUseCase.isValid(order)
                                   .flatMap(isValid -> this.checkFraud(order, isValid))
                                   .flatMap(notifyOrderValidationUseCase::notify);
    }

    private Mono<Order> checkFraud(Order order, Boolean isValid) {
        if(!isValid){
            log.warn("Fraud detected: {}", order);
            createFraudUseCase.create(order)
                              .doOnSuccess(fraud -> log.info("Fraud created successfully: {}", fraud));
        }
        return Mono.just(order);
    }

}
