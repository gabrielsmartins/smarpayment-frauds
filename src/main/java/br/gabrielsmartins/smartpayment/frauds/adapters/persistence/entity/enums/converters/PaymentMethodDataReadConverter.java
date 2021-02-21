package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.converters;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class PaymentMethodDataReadConverter implements Converter<Row, PaymentMethodData> {

    @Override
    public PaymentMethodData convert(Row source) {
        Integer paymentMethodId = source.get("payment_method_id", Integer.class);
        return PaymentMethodData.fromCode(paymentMethodId);
    }
}
