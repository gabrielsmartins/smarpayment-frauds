package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.converters;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class PaymentMethodDataWriteConverter implements Converter<PaymentMethodData, OutboundRow> {

    @Override
    public OutboundRow convert(PaymentMethodData paymentMethodData) {
        OutboundRow row = new OutboundRow();
        row.put("payment_method_id", Parameter.from(paymentMethodData.getCode()));
        return row;
    }
}
