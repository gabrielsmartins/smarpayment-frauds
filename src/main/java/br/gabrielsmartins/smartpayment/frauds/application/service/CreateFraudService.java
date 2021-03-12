package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudItem;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.CreateFraudUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.SaveFraudUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateFraudService implements CreateFraudUseCase {

    private final SaveFraudUseCase saveFraudUseCase;

    @Override
    public Mono<Fraud> create(Order order) {
        log.info("Creating fraud register for order: {}", order);
        Fraud fraud = mapToFraud(order);
        return saveFraudUseCase.save(fraud);
    }

    private Fraud mapToFraud(Order order) {
        Fraud fraud = Fraud.builder()
                           .withOrderId(order.getId())
                           .withCustomerId(order.getCustomerId())
                           .withTotalAmount(order.getTotalAmount())
                           .withTotalDiscount(order.getTotalDiscount())
                           .withCreatedAt(LocalDateTime.now())
                           .build();
        order.getItems()
             .stream()
             .map(this::mapToFraudItem)
             .forEach(fraud::addItem);

        order.getPaymentMethods()
              .forEach(fraud::addPaymentMethod);
        return fraud;
    }

    private FraudItem mapToFraudItem(OrderItem orderItem){
        return FraudItem.builder()
                .withProductId(orderItem.getProductId())
                .withQuantity(orderItem.getQuantity())
                .withAmount(orderItem.getAmount())
                .build();
    }
}
