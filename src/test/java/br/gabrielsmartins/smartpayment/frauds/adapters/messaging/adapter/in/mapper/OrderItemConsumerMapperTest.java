package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.in.mapper;

import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.smartpayment.frauds.application.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderItemConsumerMapperTest {

    private OrderItemConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemConsumerMapper();
    }

    @Test
    @DisplayName("Given Message When Map Then Return Order Item")
    public void givenMessageWhenMapThenReturnOrderItem(){
        Item message = Item.newBuilder()
                .setProductId(UUID.randomUUID().toString())
                .setQuantity(1)
                .setAmount(BigDecimal.valueOf(1500))
                .build();

        OrderItem orderItem = this.mapper.mapToDomain(message);

        assertThat(orderItem).hasNoNullFieldsOrProperties();
        assertThat(orderItem.getId()).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItem.getId().getProductId().toString()).isEqualTo(message.getProductId());
        assertThat(orderItem.getQuantity()).isEqualTo(message.getQuantity());
        assertThat(orderItem.getAmount()).isEqualTo(message.getAmount());
    }
}
