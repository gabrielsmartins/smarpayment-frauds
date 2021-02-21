package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.CreateFraudUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.NotifyOrderValidationUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.ValidateOrderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudSupport.defaultFraud;
import static br.gabrielsmartins.smartpayment.frauds.application.support.OrderSupport.defaultOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProcessOrderServiceTest {

    private ProcessOrderService service;
    private ValidateOrderUseCase validateOrderUseCase;
    private CreateFraudUseCase createFraudUseCase;
    private NotifyOrderValidationUseCase notifyOrderValidationUseCase;

    @BeforeEach
    public void setup(){
        this.validateOrderUseCase = mock(ValidateOrderUseCase.class);
        this.createFraudUseCase = mock(CreateFraudUseCase.class);
        this.notifyOrderValidationUseCase = mock(NotifyOrderValidationUseCase.class);
        this.service = new ProcessOrderService(validateOrderUseCase, createFraudUseCase, notifyOrderValidationUseCase);
    }

    @Test
    @DisplayName("Given Order When Process Then Return Processed Order")
    public void givenOrderWhenProcessThenReturnProcessedOrder(){
        Order order = defaultOrder().build();

        when(validateOrderUseCase.isValid(any(Order.class))).thenReturn(Mono.just(false));
        when(createFraudUseCase.create(any(Order.class))).thenReturn(Mono.just(defaultFraud().build()));
        when(notifyOrderValidationUseCase.notify(any(Order.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.process(order)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.validateOrderUseCase, times(1)).isValid(any(Order.class));
        verify(this.createFraudUseCase, times(1)).create(any(Order.class));
        verify(this.notifyOrderValidationUseCase, times(1)).notify(any(Order.class));
    }

}
