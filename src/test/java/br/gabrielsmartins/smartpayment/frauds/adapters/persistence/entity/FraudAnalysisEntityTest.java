package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudItemEntitySupport.defaultFraudItemEntity;
import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.PaymentMethodEntitySupport.defaultPaymentMethodEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudAnalysisEntityTest {

    @Test
    @DisplayName("Given Item When Add Then Return Items Size")
    public void givenItemWhenAddThenReturnItemsSize(){
        FraudEntity fraudEntity = new FraudEntity();
        FraudItemEntity itemEntity = defaultFraudItemEntity().build();
        Integer itemSize = fraudEntity.addItem(itemEntity);
        assertThat(itemSize).isEqualTo(1);
    }

    @Test
    @DisplayName("Given Payment Set When Add Then Return PaymentSets Size")
    public void givenPaymentSetWhenAddThenReturnPaymentSetsSize(){
        FraudEntity fraudEntity = new FraudEntity();

        PaymentMethodEntity paymentMethod = defaultPaymentMethodEntity().build();

        Integer paymentSetsSize = fraudEntity.addPaymentMethod(paymentMethod);
        assertThat(paymentSetsSize).isEqualTo(1);
    }
}
