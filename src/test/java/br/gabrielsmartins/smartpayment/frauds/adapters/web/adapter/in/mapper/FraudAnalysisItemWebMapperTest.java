package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudItemDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysisItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.frauds.adapters.web.support.FraudItemDTOSupport.defaultFraudItemDTO;
import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudItemSupport.defaultFraudItem;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudAnalysisItemWebMapperTest {

    private FraudItemWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new FraudItemWebMapper();
    }

    @Test
    @DisplayName("Given Item DTO When Map Then Return Item")
    public void givenItemDTOWhenMapThenReturnItem(){
        FraudItemDTO fraudItemDTO = defaultFraudItemDTO().build();
        FraudAnalysisItem fraudAnalysisItem = this.mapper.mapToDomain(fraudItemDTO);

        assertThat(fraudAnalysisItem).hasNoNullFieldsOrPropertiesExcept("fraudAnalysis");
        assertThat(fraudAnalysisItem.getProductId()).isEqualTo(fraudItemDTO.getProductId());
        assertThat(fraudAnalysisItem.getAmount()).isEqualTo(fraudItemDTO.getAmount());
        assertThat(fraudAnalysisItem.getQuantity()).isEqualTo(fraudItemDTO.getQuantity());
    }

    @Test
    @DisplayName("Given Item When Map Then Return Item DTO")
    public void givenItemWhenMapThenReturnItemDTO(){
        FraudAnalysisItem fraudAnalysisItem = defaultFraudItem().build();
        FraudItemDTO fraudItemDTO = this.mapper.mapToDTO(fraudAnalysisItem);

        assertThat(fraudItemDTO).hasNoNullFieldsOrProperties();
        assertThat(fraudItemDTO.getProductId()).isEqualTo(fraudAnalysisItem.getProductId());
        assertThat(fraudItemDTO.getAmount()).isEqualTo(fraudAnalysisItem.getAmount());
        assertThat(fraudItemDTO.getQuantity()).isEqualTo(fraudAnalysisItem.getQuantity());
    }
}
