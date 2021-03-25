package br.gabrielsmartins.smartpayment.adapters.web.support;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.PaymentMethodDTO;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;

import java.math.BigDecimal;

public class PaymentMethodDTOSupport {

    private PaymentMethodDTOSupport(){
        super();
    }

    public static PaymentMethodDTO.PaymentMethodDTOBuilder defaultPaymentMethodDTO(){
        return PaymentMethodDTO.builder()
                              .withPaymentMethod(PaymentMethod.CASH.name())
                              .withAmount(BigDecimal.valueOf(500));
    }
}
