package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentMethodDataRowMapper extends RowMapper<PaymentMethodEntity> {

    @Override
    public PaymentMethodEntity apply(Row row, Object o) {
        Integer paymentMethodId = parse(row,"payment_method_id", Integer.class);
        PaymentMethodData paymentMethod = PaymentMethodData.fromCode(paymentMethodId);
        BigDecimal amount = parse(row,"payment_method_amount", BigDecimal.class);
        return PaymentMethodEntity.builder()
                                  .withId(PaymentMethodEntity.PaymentMethodEntityId.builder()
                                          .withPaymentMethod(paymentMethod)
                                          .build())
                                  .withAmount(amount)
                                  .build();
    }
}
