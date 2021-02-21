package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudItem;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudSupport.defaultFraud;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FraudWebMapperTest {


    private FraudWebMapper mapper;

    @BeforeEach
    public void setup(){
        var itemWebMapper = new FraudItemWebMapper();
        this.mapper = new FraudWebMapper(itemWebMapper);
    }

    @Test
    @DisplayName("Given Fraud When Map Then Return Fraud DTO")
    public void givenFraudWhenMapThenReturnFraudDTO(){
        Fraud fraud = defaultFraud().build();

        fraud.addItem(FraudItem.builder()
                               .withId(FraudItem.FraudItemId.builder()
                                       .withProductId(UUID.randomUUID())
                                       .build())
                               .withQuantity(1)
                               .withAmount(BigDecimal.valueOf(100.00))
                               .build());

        fraud.addPaymentMethod(PaymentMethod.CREDIT_CARD, BigDecimal.TEN);


        FraudDTO fraudDTO = this.mapper.mapToDTO(fraud);

        assertThat(fraudDTO).hasNoNullFieldsOrProperties();
        assertThat(fraudDTO.getId()).isEqualTo(fraud.getId());
        assertThat(fraudDTO.getCreatedAt()).isEqualTo(fraud.getCreatedAt());
        assertThat(fraudDTO.getCustomerId()).isEqualTo(fraud.getCustomerId());
        assertThat(fraudDTO.getOrderId()).isEqualTo(fraud.getOrderId());
        assertThat(fraudDTO.getTotalAmount()).isEqualTo(fraud.getTotalAmount());
        assertThat(fraudDTO.getTotalDiscount()).isEqualTo(fraud.getTotalDiscount());
        assertThat(fraudDTO.getPaymentMethods().size()).isEqualTo(fraud.getPaymentMethods().size());
        assertThat(fraudDTO.getItems().size()).isEqualTo(fraud.getItems().size());
    }
}
