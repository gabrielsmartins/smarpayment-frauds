package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FraudItemEntityRowMapper extends RowMapper<FraudItemEntity> {

    @Override
    public FraudItemEntity apply(Row row, Object o) {
        UUID productId = parse(row,"product_id", UUID.class);
        Long quantity = parse(row, "item_quantity", Long.class);
        BigDecimal amount = parse(row,"item_amount", BigDecimal.class);
        return FraudItemEntity.builder()
                               .withId(FraudItemEntity.FraudItemEntityId.builder()
                                       .withProductId(productId)
                                       .build())
                               .withQuantity(quantity)
                               .withAmount(amount)
                               .build();
    }
}
