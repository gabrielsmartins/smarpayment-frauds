package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveFraudUseCase;
import br.gabrielsmartins.smartpayment.application.support.OrderSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class CreateFraudAnalysisServiceTest {

    private CreateFraudAnalysisService service;
    private SaveFraudUseCase saveFraudUseCase;

    @BeforeEach
    public void setup(){
        this.saveFraudUseCase = mock(SaveFraudUseCase.class);
        this.service = new CreateFraudAnalysisService(saveFraudUseCase);
    }

    @Test
    @DisplayName("Given Order When Is Valid Then Create Fraud")
    public void givenOrderWhenIsValidThenCreateFraud(){
        Order order = OrderSupport.defaultOrder().build();

        when(saveFraudUseCase.save(any(FraudAnalysis.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.create(order, false)
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

        verify(this.saveFraudUseCase, times(1)).save(any(FraudAnalysis.class));
    }

}
