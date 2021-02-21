package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class FraudItemEntityMapper implements BiFunction<Row, Object, FraudItemEntity> {

    @Override
    public FraudItemEntity apply(Row row, Object o) {
        UUID productId = row.get("product_id", UUID.class);
        Integer quantity = row.get("item_quantity", Integer.class);
        BigDecimal amount = row.get("item_amount", BigDecimal.class);
        return FraudItemEntity.builder()
                           .withId(FraudItemEntity.FraudItemEntityId.builder()
                                   .withProductId(productId)
                                   .build())
                           .withQuantity(quantity)
                           .withAmount(amount)
                           .build();
    }
}
