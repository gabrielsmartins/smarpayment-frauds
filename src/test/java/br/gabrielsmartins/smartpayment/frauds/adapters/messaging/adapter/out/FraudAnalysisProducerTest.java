package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out;

import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudItemSupport.defaultFraudItem;
import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudSupport.defaultFraud;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestChannelBinderConfiguration.class)
public class FraudAnalysisProducerTest {

    @Autowired
    private FraudProducer producer;

    @Autowired
    private OutputDestination outputDestination;

    @Test
    @DisplayName("Given Fraud When Is Valid Then Send Message")
    public void givenFraudWhenIsValidThenSendMessage(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        fraudAnalysis.addItem(defaultFraudItem().build());
        fraudAnalysis.addPaymentMethod(PaymentMethod.CASH, BigDecimal.TEN);

        this.producer.notify(fraudAnalysis)
                     .as(StepVerifier::create)
                     .expectNextCount(1)
                     .verifyComplete();

        Message<byte[]> message = outputDestination.receive(1000);

        assertThat(message).isNotNull();
    }

    @Test
    @DisplayName("Given Order When Is Valid Then Send Message")
    public void givenOrderWhenIsValidThenSendMessage(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        fraudAnalysis.addItem(defaultFraudItem().build());
        fraudAnalysis.addPaymentMethod(PaymentMethod.CASH, BigDecimal.TEN);

        this.producer.notify(fraudAnalysis)
                     .as(StepVerifier::create)
                     .expectNextCount(1)
                     .verifyComplete();

        Message<byte[]> message = outputDestination.receive(1000);

        assertThat(message).isNotNull();
    }

}
