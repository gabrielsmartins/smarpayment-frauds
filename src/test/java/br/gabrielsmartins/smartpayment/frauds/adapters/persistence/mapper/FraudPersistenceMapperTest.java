package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudEntitySupport;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudSupport.defaultFraud;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudPersistenceMapperTest {

    private FraudPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        var itemPersistenceMapper = new FraudItemPersistenceMapper();
        var paymentMethodPersistenceMapper =  new PaymentMethodPersistenceMapper();
        this.mapper = new FraudPersistenceMapper(itemPersistenceMapper, paymentMethodPersistenceMapper);
    }

    @Test
    @DisplayName("Given Fraud When Map Then Return Fraud Entity")
    public void givenFraudWhenMapThenReturnFraudEntity(){
        Fraud fraud = defaultFraud().build();

        FraudEntity fraudEntity = this.mapper.mapToEntity(fraud);

        assertThat(fraudEntity).hasNoNullFieldsOrProperties();
        assertThat(fraudEntity.getId()).isEqualTo(fraud.getId());
        assertThat(fraudEntity.getOrderId()).isEqualTo(fraud.getOrderId());
        assertThat(fraudEntity.getCustomerId()).isEqualTo(fraud.getCustomerId());
        assertThat(fraudEntity.getCreatedAt()).isEqualTo(fraud.getCreatedAt());
        assertThat(fraudEntity.getTotalAmount()).isEqualTo(fraud.getTotalAmount());
        assertThat(fraudEntity.getTotalDiscount()).isEqualTo(fraud.getTotalDiscount());
        assertThat(fraudEntity.getItems().size()).isEqualTo(fraud.getItems().size());
        assertThat(fraudEntity.getPaymentMethods().size()).isEqualTo(fraud.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Fraud Entity When Map Then Return Fraud")
    public void givenFraudEntityWhenMapThenReturnFraud(){
        FraudEntity fraudEntity = FraudEntitySupport.defaultFraudEntity().build();

        Fraud fraud = this.mapper.mapToDomain(fraudEntity);

        assertThat(fraud).hasNoNullFieldsOrProperties();
        assertThat(fraud.getId()).isEqualTo(fraudEntity.getId());
        assertThat(fraud.getOrderId()).isEqualTo(fraudEntity.getOrderId());
        assertThat(fraud.getCustomerId()).isEqualTo(fraudEntity.getCustomerId());
        assertThat(fraud.getCreatedAt()).isEqualTo(fraudEntity.getCreatedAt());
        assertThat(fraud.getTotalAmount()).isEqualTo(fraudEntity.getTotalAmount());
        assertThat(fraud.getTotalDiscount()).isEqualTo(fraudEntity.getTotalDiscount());
        assertThat(fraud.getItems().size()).isEqualTo(fraudEntity.getItems().size());
        assertThat(fraud.getPaymentMethods().size()).isEqualTo(fraudEntity.getPaymentMethods().size());
    }
}
