package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.support.FraudEntitySupport;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.application.support.FraudSupport.defaultFraud;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudAnalysisPersistenceMapperTest {

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
        FraudAnalysis fraudAnalysis = defaultFraud().build();

        FraudEntity fraudEntity = this.mapper.mapToEntity(fraudAnalysis);

        assertThat(fraudEntity).hasNoNullFieldsOrProperties();
        assertThat(fraudEntity.getId()).isEqualTo(fraudAnalysis.getId());
        assertThat(fraudEntity.getOrderId()).isEqualTo(fraudAnalysis.getOrderId());
        assertThat(fraudEntity.getCustomerId()).isEqualTo(fraudAnalysis.getCustomerId());
        assertThat(fraudEntity.getCreatedAt()).isEqualTo(fraudAnalysis.getCreatedAt());
        assertThat(fraudEntity.getTotalAmount()).isEqualTo(fraudAnalysis.getTotalAmount());
        assertThat(fraudEntity.getTotalDiscount()).isEqualTo(fraudAnalysis.getTotalDiscount());
        assertThat(fraudEntity.getItems().size()).isEqualTo(fraudAnalysis.getItems().size());
        assertThat(fraudEntity.getPaymentMethods().size()).isEqualTo(fraudAnalysis.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Fraud Entity When Map Then Return Fraud")
    public void givenFraudEntityWhenMapThenReturnFraud(){
        FraudEntity fraudEntity = FraudEntitySupport.defaultFraudEntity().build();

        FraudAnalysis fraudAnalysis = this.mapper.mapToDomain(fraudEntity);

        assertThat(fraudAnalysis).hasNoNullFieldsOrProperties();
        assertThat(fraudAnalysis.getId()).isEqualTo(fraudEntity.getId());
        assertThat(fraudAnalysis.getOrderId()).isEqualTo(fraudEntity.getOrderId());
        assertThat(fraudAnalysis.getCustomerId()).isEqualTo(fraudEntity.getCustomerId());
        assertThat(fraudAnalysis.getCreatedAt()).isEqualTo(fraudEntity.getCreatedAt());
        assertThat(fraudAnalysis.getTotalAmount()).isEqualTo(fraudEntity.getTotalAmount());
        assertThat(fraudAnalysis.getTotalDiscount()).isEqualTo(fraudEntity.getTotalDiscount());
        assertThat(fraudAnalysis.getItems().size()).isEqualTo(fraudEntity.getItems().size());
        assertThat(fraudAnalysis.getPaymentMethods().size()).isEqualTo(fraudEntity.getPaymentMethods().size());
    }
}
