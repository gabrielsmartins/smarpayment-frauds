package br.gabrielsmartins.smartpayment.frauds.application.domain;

import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudItemSupport.defaultFraudItem;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudTest {

    @Test
    @DisplayName("Given Item When Add Then Return Items Size")
    public void givenItemWhenAddThenReturnItemsSize(){
        Fraud fraud = new Fraud();
        FraudItem item = defaultFraudItem().build();
        Integer itemSize = fraud.addItem(item);
        assertThat(itemSize).isEqualTo(1);
        assertThat(item.getFraud()).isNotNull();
    }

    @Test
    @DisplayName("Given Payment Set When Add Then Return PaymentSets Size")
    public void givenPaymentSetWhenAddThenReturnPaymentSetsSize(){
        Fraud fraud = new Fraud();
        Integer paymentSetsSize = fraud.addPaymentMethod(PaymentMethod.CASH, BigDecimal.valueOf(500));
        assertThat(paymentSetsSize).isEqualTo(1);
    }

}
