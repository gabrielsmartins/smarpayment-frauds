package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysisItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudItemEntitySupport.defaultFraudItemEntity;
import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudItemSupport.defaultFraudItem;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudAnalysisItemPersistenceMapperTest {

    private FraudItemPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new FraudItemPersistenceMapper();
    }

    @Test
    @DisplayName("Given Fraud Item When Map Then Return Fraud Item Entity")
    public void givenFraudWhenMapThenReturnFraudEntity(){

        FraudAnalysisItem fraudAnalysisItem = defaultFraudItem().build();

        FraudItemEntity fraudItemEntity = this.mapper.mapToEntity(fraudAnalysisItem);

        assertThat(fraudItemEntity).hasNoNullFieldsOrPropertiesExcept("fraud");
        assertThat(fraudItemEntity.getProductId()).isEqualTo(fraudAnalysisItem.getProductId());
        assertThat(fraudItemEntity.getQuantity()).isEqualTo(fraudAnalysisItem.getQuantity());
        assertThat(fraudItemEntity.getAmount()).isEqualTo(fraudAnalysisItem.getAmount());
    }

    @Test
    @DisplayName("Given Fraud Item Entity When Map Then Return Fraud Item")
    public void givenFraudItemEntityWhenMapThenReturnFraud(){
        FraudItemEntity fraudItemEntity = defaultFraudItemEntity().build();

        FraudAnalysisItem fraudAnalysisItem = this.mapper.mapToDomain(fraudItemEntity);

        assertThat(fraudAnalysisItem).hasNoNullFieldsOrPropertiesExcept("fraudAnalysis");
        assertThat(fraudAnalysisItem.getProductId()).isEqualTo(fraudItemEntity.getProductId());
        assertThat(fraudAnalysisItem.getQuantity()).isEqualTo(fraudItemEntity.getQuantity());
        assertThat(fraudAnalysisItem.getAmount()).isEqualTo(fraudItemEntity.getAmount());
    }
}
