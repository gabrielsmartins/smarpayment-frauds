package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FraudItemPersistenceMapperTest {

    private FraudItemPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new FraudItemPersistenceMapper();
    }

    @Test
    @DisplayName("Given Fraud Item When Map Then Return Fraud Item Entity")
    public void givenFraudWhenMapThenReturnFraudEntity(){
        FraudItem fraudItem = FraudItem.builder()
                .withId(FraudItem.FraudItemId.builder()
                        .withProductId(UUID.randomUUID())
                        .build())
                .withQuantity(1L)
                .withAmount(BigDecimal.valueOf(500))
                .build();

        FraudItemEntity fraudItemEntity = this.mapper.mapToEntity(fraudItem);

        assertThat(fraudItemEntity).hasNoNullFieldsOrProperties();
        assertThat(fraudItemEntity.getId()).hasNoNullFieldsOrPropertiesExcept("fraud");
        assertThat(fraudItemEntity.getId().getProductId()).isEqualTo(fraudItem.getId().getProductId());
        assertThat(fraudItemEntity.getQuantity()).isEqualTo(fraudItem.getQuantity());
        assertThat(fraudItemEntity.getAmount()).isEqualTo(fraudItem.getAmount());
    }

    @Test
    @DisplayName("Given Fraud Item Entity When Map Then Return Fraud Item")
    public void givenFraudItemEntityWhenMapThenReturnFraud(){
        FraudItemEntity fraudItemEntity = FraudItemEntity.builder()
                .withId(FraudItemEntity.FraudItemEntityId.builder()
                        .withProductId(UUID.randomUUID())
                        .build())
                .withQuantity(1L)
                .withAmount(BigDecimal.valueOf(500))
                .build();

        FraudItem fraudItem = this.mapper.mapToDomain(fraudItemEntity);

        assertThat(fraudItem).hasNoNullFieldsOrProperties();
        assertThat(fraudItem.getId()).hasNoNullFieldsOrPropertiesExcept("fraud");
        assertThat(fraudItem.getId().getProductId()).isEqualTo(fraudItemEntity.getId().getProductId());
        assertThat(fraudItem.getQuantity()).isEqualTo(fraudItemEntity.getQuantity());
        assertThat(fraudItem.getAmount()).isEqualTo(fraudItemEntity.getAmount());
    }
}
