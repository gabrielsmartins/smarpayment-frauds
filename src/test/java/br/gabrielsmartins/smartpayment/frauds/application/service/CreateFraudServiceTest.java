package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.SaveFraudUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.frauds.application.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class CreateFraudServiceTest {

    private CreateFraudService service;
    private SaveFraudUseCase saveFraudUseCase;

    @BeforeEach
    public void setup(){
        this.saveFraudUseCase = mock(SaveFraudUseCase.class);
        this.service = new CreateFraudService(saveFraudUseCase);
    }

    @Test
    @DisplayName("Given Order When Is Valid Then Create Fraud")
    public void givenOrderWhenIsValidThenCreateFraud(){
        Order order = defaultOrder().build();

        when(saveFraudUseCase.save(any(Fraud.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.create(order)
                     .as(StepVerifier::create)
                     .assertNext(fraud -> {
                         assertThat(fraud.getCreatedAt()).isNotNull();
                         assertThat(fraud.getCustomerId()).isEqualTo(order.getCustomerId());
                         assertThat(fraud.getOrderId()).isEqualTo(order.getId());
                         assertThat(fraud.getTotalAmount()).isEqualTo(order.getTotalAmount());
                         assertThat(fraud.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
                         assertThat(fraud.getItems().size()).isEqualTo(order.getItems().size());
                         assertThat(fraud.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
                     });

        verify(this.saveFraudUseCase, times(1)).save(any(Fraud.class));
    }

}
