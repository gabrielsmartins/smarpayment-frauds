package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.schemas.fraud_detected.FraudDetected;
import br.gabrielsmartins.schemas.fraud_discarded.FraudDiscarded;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysisItem;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.gabrielsmartins.smartpayment.application.support.FraudItemSupport.defaultFraudItem;
import static br.gabrielsmartins.smartpayment.application.support.FraudSupport.defaultFraud;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudAnalysisProducerMapperTest {

    private FraudProducerMapper mapper;

    @BeforeEach
    public void setup(){
        var itemProducerMapper = new FraudItemProducerMapper();
        var paymentMethodProducerMapper = new PaymentMethodProducerMapper();
        this.mapper = new FraudProducerMapper(itemProducerMapper, paymentMethodProducerMapper);
    }

    @Test
    @DisplayName("Given Fraud When Map Then Return Message")
    public void givenFraudWhenMapThenReturnMessage(){

        FraudAnalysis fraudAnalysis = defaultFraud()
                                         .build();

        FraudAnalysisItem fraudAnalysisItem = defaultFraudItem().build();

        fraudAnalysis.addItem(fraudAnalysisItem);
        fraudAnalysis.addPaymentMethod(PaymentMethod.CASH, BigDecimal.TEN);

        FraudDetected fraudDetected = this.mapper.mapToFraudDetectedMessage(fraudAnalysis);

        assertThat(fraudDetected).hasNoNullFieldsOrProperties();
        assertThat(fraudDetected.getId()).isEqualTo(fraudAnalysis.getId());
        assertThat(fraudDetected.getValidatedAt()).isNotNull();
        assertThat(fraudDetected.getCreatedAt()).isEqualTo(fraudAnalysis.getCreatedAt());
        assertThat(fraudDetected.getFraud()).isEqualTo(fraudAnalysis.isFraud());
        assertThat(fraudDetected.getCustomerId()).isEqualTo(fraudAnalysis.getCustomerId().toString());
        assertThat(fraudDetected.getTotalAmount()).isEqualTo(fraudAnalysis.getTotalAmount());
        assertThat(fraudDetected.getTotalDiscount()).isEqualTo(fraudAnalysis.getTotalDiscount());
        assertThat(fraudDetected.getItems().size()).isEqualTo(fraudAnalysis.getItems().size());
        assertThat(fraudDetected.getPaymentMethods().size()).isEqualTo(fraudAnalysis.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Fraud When Map Then Return Fraud Discarded Message")
    public void givenFraudWhenMapThenReturnFraudDiscardedMessage(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        FraudAnalysisItem fraudAnalysisItem = defaultFraudItem().build();

        fraudAnalysis.addItem(fraudAnalysisItem);
        fraudAnalysis.addPaymentMethod(PaymentMethod.CASH, BigDecimal.TEN);

        FraudDiscarded fraudDiscarded = this.mapper.mapToFraudDiscardedMessage(fraudAnalysis);

        assertThat(fraudDiscarded).hasNoNullFieldsOrProperties();
        assertThat(fraudDiscarded.getId()).isEqualTo(fraudAnalysis.getId());
        assertThat(fraudDiscarded.getValidatedAt()).isNotNull();
        assertThat(fraudDiscarded.getCreatedAt()).isEqualTo(fraudAnalysis.getCreatedAt());
        assertThat(fraudDiscarded.getFraud()).isEqualTo(fraudAnalysis.isFraud());
        assertThat(fraudDiscarded.getCustomerId()).isEqualTo(fraudAnalysis.getCustomerId().toString());
        assertThat(fraudDiscarded.getTotalAmount()).isEqualTo(fraudAnalysis.getTotalAmount());
        assertThat(fraudDiscarded.getTotalDiscount()).isEqualTo(fraudAnalysis.getTotalDiscount());
        assertThat(fraudDiscarded.getItems().size()).isEqualTo(fraudAnalysis.getItems().size());
        assertThat(fraudDiscarded.getPaymentMethods().size()).isEqualTo(fraudAnalysis.getPaymentMethods().size());
    }
}
