package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class FraudEntityMapper implements BiFunction<Row, Object, FraudEntity> {

    private final FraudItemEntityMapper fraudItemEntityMapper;
    private final PaymentMethodDataMapper paymentMethodDataMapper;

    @Override
    public FraudEntity apply(Row row, Object o) {
        UUID fraudId = row.get("fraud_id", UUID.class);
        Long orderId = row.get("order_id", Long.class);
        UUID customerId = row.get("customer_id", UUID.class);
        LocalDateTime createdAt = row.get("created_at", LocalDateTime.class);
        BigDecimal totalAmount = row.get("total_amount", BigDecimal.class);
        BigDecimal totalDiscount = row.get("total_discount", BigDecimal.class);
        //fraudItemEntityMapper.apply(row,o);
        //Map<PaymentMethodData, BigDecimal> paymentMethods = paymentMethodDataMapper.apply(row, o);
        return FraudEntity.builder()
                           .withId(fraudId)
                           .withOrderId(orderId)
                           .withCustomerId(customerId)
                           .withCreatedAt(createdAt)
                           .withTotalAmount(totalAmount)
                           .withTotalDiscount(totalDiscount)
                           .build();
    }
}
