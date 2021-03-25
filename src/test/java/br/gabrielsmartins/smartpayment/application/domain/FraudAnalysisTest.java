package br.gabrielsmartins.smartpayment.application.domain;

import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.support.FraudItemSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudAnalysisTest {

    @Test
    @DisplayName("Given Item When Add Then Return Items Size")
    public void givenItemWhenAddThenReturnItemsSize(){
        FraudAnalysis fraudAnalysis = new FraudAnalysis();
        FraudAnalysisItem item = FraudItemSupport.defaultFraudItem().build();
        Integer itemSize = fraudAnalysis.addItem(item);
        assertThat(itemSize).isEqualTo(1);
        assertThat(item.getFraudAnalysis()).isNotNull();
    }

    @Test
    @DisplayName("Given Payment Set When Add Then Return PaymentSets Size")
    public void givenPaymentSetWhenAddThenReturnPaymentSetsSize(){
        FraudAnalysis fraudAnalysis = new FraudAnalysis();
        Integer paymentSetsSize = fraudAnalysis.addPaymentMethod(PaymentMethod.CASH, BigDecimal.valueOf(500));
        assertThat(paymentSetsSize).isEqualTo(1);
    }

}
