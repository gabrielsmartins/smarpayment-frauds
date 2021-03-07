package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FraudEntityRowMapper extends RowMapper<FraudEntity> {

    @Override
    public FraudEntity apply(Row row, Object o) {
        UUID fraudId = parse(row, "fraud_id", UUID.class);
        Long orderId = parse(row,"order_id", Long.class);
        UUID customerId = parse(row,"customer_id", UUID.class);
        LocalDateTime createdAt = parse(row,"created_at", LocalDateTime.class);
        BigDecimal totalAmount = parse(row,"total_amount", BigDecimal.class);
        BigDecimal totalDiscount = parse(row,"total_discount", BigDecimal.class);
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
