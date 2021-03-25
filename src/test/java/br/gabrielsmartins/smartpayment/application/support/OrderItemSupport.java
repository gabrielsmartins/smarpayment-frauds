package br.gabrielsmartins.smartpayment.application.support;

import br.gabrielsmartins.smartpayment.application.domain.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemSupport {

    private OrderItemSupport(){
        super();
    }

    public static OrderItem.OrderItemBuilder defaultOrderItem(){
        return OrderItem.builder()
                        .withProductId(UUID.randomUUID())
                        .withQuantity(1)
                        .withAmount(BigDecimal.valueOf(500));
    }
}
