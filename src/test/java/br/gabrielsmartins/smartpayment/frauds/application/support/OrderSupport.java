package br.gabrielsmartins.smartpayment.frauds.application.support;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order.OrderBuilder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderSupport {

    private OrderSupport(){
        super();
    }

    public static OrderBuilder defaultOrder(){
        return Order.builder()
                    .withCreatedAt(LocalDateTime.now())
                    .withTotalAmount(BigDecimal.valueOf(1500))
                    .withTotalDiscount(BigDecimal.valueOf(500))
                    .withCustomerId(UUID.randomUUID())
                    .withId(12345L);
    }
}
