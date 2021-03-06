package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Component
public class PaymentMethodDataRowMapper extends RowMapper<Map<PaymentMethodData, BigDecimal>> {

    @Override
    public Map<PaymentMethodData, BigDecimal> apply(Row row, Object o) {
        Integer paymentMethodId = parse(row,"payment_method_id", Integer.class);
        BigDecimal amount = parse(row,"payment_method_amount", BigDecimal.class);
        return Map.of(PaymentMethodData.fromCode(paymentMethodId), Objects.requireNonNull(amount));
    }
}
