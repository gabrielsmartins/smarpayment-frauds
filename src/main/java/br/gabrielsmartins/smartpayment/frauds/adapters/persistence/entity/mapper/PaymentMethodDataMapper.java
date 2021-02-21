package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class PaymentMethodDataMapper implements BiFunction<Row, Object, Map<PaymentMethodData, BigDecimal>> {

    @Override
    public Map<PaymentMethodData, BigDecimal> apply(Row row, Object o) {
        Integer paymentMethodId = row.get("payment_method_id", Integer.class);
        BigDecimal amount = row.get("payment_method_amount", BigDecimal.class);
        return Map.of(PaymentMethodData.fromCode(paymentMethodId), amount);
    }
}
