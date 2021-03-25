package br.gabrielsmartins.smartpayment.adapters.persistence.entity;


import br.gabrielsmartins.smartpayment.adapters.persistence.entity.enums.PaymentMethodData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodEntity {

    @Field("payment_type")
    private PaymentMethodData paymentMethod;

    @Field("payment_amount")
    private BigDecimal amount;

}
