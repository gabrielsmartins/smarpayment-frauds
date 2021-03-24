package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysisItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudItemSupport.defaultFraudItem;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudAnalysisItemProducerMapperTest {

    private FraudItemProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new FraudItemProducerMapper();
    }

    @Test
    @DisplayName("Given Fraud Item When Map Then Return Fraud Detected Message")
    public void givenFraudItemWhenMapThenReturnFraudDetectedMessage(){

        FraudAnalysisItem fraudAnalysisItem = defaultFraudItem().build();

        var item = this.mapper.mapToFraudDetectedMessage(fraudAnalysisItem);

        assertThat(item).hasNoNullFieldsOrProperties();
        assertThat(item.getProductId()).isEqualTo(fraudAnalysisItem.getProductId().toString());
        assertThat(item.getAmount()).isEqualTo(fraudAnalysisItem.getAmount());
        assertThat(item.getQuantity()).isEqualTo(fraudAnalysisItem.getQuantity().intValue());
    }

    @Test
    @DisplayName("Given Fraud Item When Map Then Return Fraud Discarded Message")
    public void givenFraudItemWhenMapThenReturnFraudDiscardedMessage(){

        FraudAnalysisItem fraudAnalysisItem = defaultFraudItem().build();

        var item = this.mapper.mapToFraudDetectedMessage(fraudAnalysisItem);

        assertThat(item).hasNoNullFieldsOrProperties();
        assertThat(item.getProductId()).isEqualTo(fraudAnalysisItem.getProductId().toString());
        assertThat(item.getAmount()).isEqualTo(fraudAnalysisItem.getAmount());
        assertThat(item.getQuantity()).isEqualTo(fraudAnalysisItem.getQuantity().intValue());
    }



}
