package br.gabrielsmartins.smartpayment.adapters.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudItemEntity {

    @Field("product_id")
    private UUID productId;

    @Field("quantity")
    private Integer quantity;

    @Field("item_amount")
    private BigDecimal amount;


}
