package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysisItem;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudItemSupport.defaultFraudItem;
import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudSupport.defaultFraud;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FraudAnalysisWebMapperTest {


    private FraudWebMapper mapper;

    @BeforeEach
    public void setup(){
        var itemWebMapper = new FraudItemWebMapper();
        this.mapper = new FraudWebMapper(itemWebMapper);
    }

    @Test
    @DisplayName("Given Fraud When Map Then Return Fraud DTO")
    public void givenFraudWhenMapThenReturnFraudDTO(){
        FraudAnalysis fraudAnalysis = defaultFraud().build();
        FraudAnalysisItem fraudAnalysisItem = defaultFraudItem().build();

        fraudAnalysis.addItem(fraudAnalysisItem);
        fraudAnalysis.addPaymentMethod(PaymentMethod.CREDIT_CARD, BigDecimal.TEN);

        FraudDTO fraudDTO = this.mapper.mapToDTO(fraudAnalysis);

        assertThat(fraudDTO).hasNoNullFieldsOrProperties();
        assertThat(fraudDTO.getId()).isEqualTo(fraudAnalysis.getId());
        assertThat(fraudDTO.getCreatedAt()).isEqualTo(fraudAnalysis.getCreatedAt());
        assertThat(fraudDTO.getCustomerId()).isEqualTo(fraudAnalysis.getCustomerId());
        assertThat(fraudDTO.getOrderId()).isEqualTo(fraudAnalysis.getOrderId());
        assertThat(fraudDTO.getTotalAmount()).isEqualTo(fraudAnalysis.getTotalAmount());
        assertThat(fraudDTO.getTotalDiscount()).isEqualTo(fraudAnalysis.getTotalDiscount());
        assertThat(fraudDTO.getPaymentMethods().size()).isEqualTo(fraudAnalysis.getPaymentMethods().size());
        assertThat(fraudDTO.getItems().size()).isEqualTo(fraudAnalysis.getItems().size());
    }
}
