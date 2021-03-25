package br.gabrielsmartins.smartpayment.application.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {

    CREDIT_CARD("CREDIT CARD"),
    CASH ("CASH"),
    INTERNET_BANKING("INTERNET BANKING"),
    PAYPAL("PAYPAL");

    @JsonValue
    private final String description;

}
