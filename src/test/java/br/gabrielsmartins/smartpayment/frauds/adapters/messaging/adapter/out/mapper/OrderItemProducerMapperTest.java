package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.schemas.order_validated.Item;
import br.gabrielsmartins.smartpayment.frauds.application.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderItemProducerMapperTest {

    private OrderItemProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemProducerMapper();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Message")
    public void givenOrderItemWhenMapThenReturnMessage(){

        OrderItem orderItem = OrderItem.builder()
                                        .withId(OrderItem.OrderItemId.builder()
                                                .withProductId(UUID.randomUUID())
                                                .build())
                                        .withQuantity(1)
                                        .withAmount(BigDecimal.valueOf(1500))
                                        .build();

        Item item = this.mapper.mapToMessage(orderItem);

        assertThat(item).hasNoNullFieldsOrProperties();
        assertThat(item.getProductId()).isEqualTo(orderItem.getId().getProductId().toString());
        assertThat(item.getAmount()).isEqualTo(orderItem.getAmount());
        assertThat(item.getQuantity()).isEqualTo(orderItem.getQuantity());
    }

}
