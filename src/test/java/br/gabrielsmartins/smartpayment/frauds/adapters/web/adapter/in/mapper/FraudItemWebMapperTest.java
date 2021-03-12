package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudItemDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.frauds.adapters.web.support.FraudItemDTOSupport.defaultFraudItemDTO;
import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudItemSupport.defaultFraudItem;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudItemWebMapperTest {

    private FraudItemWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new FraudItemWebMapper();
    }

    @Test
    @DisplayName("Given Item DTO When Map Then Return Item")
    public void givenItemDTOWhenMapThenReturnItem(){
        FraudItemDTO fraudItemDTO = defaultFraudItemDTO().build();
        FraudItem fraudItem = this.mapper.mapToDomain(fraudItemDTO);

        assertThat(fraudItem).hasNoNullFieldsOrProperties();
        assertThat(fraudItem).hasNoNullFieldsOrPropertiesExcept("fraud");
        assertThat(fraudItem.getProductId()).isEqualTo(fraudItemDTO.getProductId());
        assertThat(fraudItem.getAmount()).isEqualTo(fraudItemDTO.getAmount());
        assertThat(fraudItem.getQuantity()).isEqualTo(fraudItemDTO.getQuantity());
    }

    @Test
    @DisplayName("Given Item When Map Then Return Item DTO")
    public void givenItemWhenMapThenReturnItemDTO(){
        FraudItem fraudItem = defaultFraudItem().build();
        FraudItemDTO fraudItemDTO = this.mapper.mapToDTO(fraudItem);

        assertThat(fraudItemDTO).hasNoNullFieldsOrProperties();
        assertThat(fraudItemDTO.getProductId()).isEqualTo(fraudItem.getProductId());
        assertThat(fraudItemDTO.getAmount()).isEqualTo(fraudItem.getAmount());
        assertThat(fraudItemDTO.getQuantity()).isEqualTo(fraudItem.getQuantity());
    }
}
