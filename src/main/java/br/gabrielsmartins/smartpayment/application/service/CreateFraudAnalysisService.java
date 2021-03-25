package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysisItem;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.application.ports.in.CreateFraudUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveFraudUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateFraudAnalysisService implements CreateFraudUseCase {

    private final SaveFraudUseCase saveFraudUseCase;

    @Override
    public Mono<FraudAnalysis> create(Order order, boolean isFraud) {
        log.info("Creating fraud register for order: {}", order);
        FraudAnalysis fraudAnalysis = mapToFraud(order);
        fraudAnalysis.setFraud(isFraud);
        return saveFraudUseCase.save(fraudAnalysis);
    }

    private FraudAnalysis mapToFraud(Order order) {
        FraudAnalysis fraudAnalysis = FraudAnalysis.builder()
                           .withOrderId(order.getId())
                           .withCustomerId(order.getCustomerId())
                           .withTotalAmount(order.getTotalAmount())
                           .withTotalDiscount(order.getTotalDiscount())
                           .withCreatedAt(LocalDateTime.now())
                           .build();
        order.getItems()
             .stream()
             .map(this::mapToFraudItem)
             .forEach(fraudAnalysis::addItem);

        order.getPaymentMethods()
              .forEach(fraudAnalysis::addPaymentMethod);
        return fraudAnalysis;
    }

    private FraudAnalysisItem mapToFraudItem(OrderItem orderItem){
        return FraudAnalysisItem.builder()
                .withProductId(orderItem.getProductId())
                .withQuantity(orderItem.getQuantity())
                .withAmount(orderItem.getAmount())
                .build();
    }
}
