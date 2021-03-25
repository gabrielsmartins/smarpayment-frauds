package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.CreateFraudUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.NotifyOrderValidationUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.ProcessOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.ValidateOrderUseCase;
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
    public Mono<FraudAnalysis> process(Order order) {
        log.info("Processing order: {}", order);
        return validateOrderUseCase.isValid(order)
                                   .flatMap(isValid -> this.createFraudAnalysis(order, !isValid))
                                   .flatMap(this::notifyFraudAnalysis);
    }

    private Mono<FraudAnalysis> createFraudAnalysis(Order order, boolean isFraud) {
        return createFraudUseCase.create(order, isFraud)
                                 .doOnSuccess(fraud -> log.info("Fraud created successfully: {}", fraud));
    }

    private Mono<FraudAnalysis> notifyFraudAnalysis(FraudAnalysis fraudAnalysis){
        return this.notifyOrderValidationUseCase.notify(fraudAnalysis)
                                                .doOnSuccess(it -> log.info("Fraud analysis sent successfully: {}", fraudAnalysis));
    }

}
