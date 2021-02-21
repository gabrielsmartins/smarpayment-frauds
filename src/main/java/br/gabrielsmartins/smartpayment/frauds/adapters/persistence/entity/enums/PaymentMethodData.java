package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums;

import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum PaymentMethodData {

    CREDIT_CARD(1, PaymentMethod.CREDIT_CARD),
    CASH(2, PaymentMethod.CASH),
    INTERNET_BANKING(3, PaymentMethod.INTERNET_BANKING),
    PAYPAL(4, PaymentMethod.PAYPAL);

    private final Integer code;
    private final PaymentMethod paymentMethod;


    public static PaymentMethodData fromCode(Integer code) {
        return Stream.of(PaymentMethodData.values())
                .filter(paymentMethodData -> paymentMethodData.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public static PaymentMethodData fromEnum(PaymentMethod paymentMethod) {
        return Stream.of(PaymentMethodData.values())
                     .filter(paymentMethodData -> paymentMethodData.getPaymentMethod() == paymentMethod)
                     .findFirst()
                     .orElse(null);
    }
}
