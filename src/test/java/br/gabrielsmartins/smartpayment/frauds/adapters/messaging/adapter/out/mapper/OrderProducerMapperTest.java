package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.schemas.order_validated.OrderValidated;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static br.gabrielsmartins.smartpayment.frauds.application.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderProducerMapperTest {

    private OrderProducerMapper mapper;

    @BeforeEach
    public void setup(){
        var itemProducerMapper = new OrderItemProducerMapper();
        var paymentMethodProducerMapper = new PaymentMethodProducerMapper();
        this.mapper = new OrderProducerMapper(itemProducerMapper, paymentMethodProducerMapper);
    }

    @Test
    @DisplayName("Given Order When Map Then Return Message")
    public void givenOrderWhenMapThenReturnMessage(){

        Order order = defaultOrder().build();

        order.addItem(OrderItem.builder()
                                .withProductId(UUID.randomUUID())
                                .withAmount(BigDecimal.TEN)
                                .withQuantity(1)
                                .build());
        order.addPaymentMethod(PaymentMethod.CASH, BigDecimal.TEN);

        OrderValidated orderValidated = this.mapper.mapToMessage(order);

        assertThat(orderValidated).hasNoNullFieldsOrProperties();
        assertThat(orderValidated.getId()).isEqualTo(order.getId());
        assertThat(orderValidated.getValidatedAt()).isNotNull();
        assertThat(orderValidated.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(orderValidated.getFraud()).isEqualTo(order.isFraud());
        assertThat(orderValidated.getCustomerId()).isEqualTo(order.getCustomerId().toString());
        assertThat(orderValidated.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderValidated.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(orderValidated.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(orderValidated.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }
}
