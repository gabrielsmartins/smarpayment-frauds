package br.gabrielsmartins.smartpayment.frauds.adapters.web.support;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FraudDTOSupport {

    private FraudDTOSupport(){
        super();
    }

    public static FraudDTO.FraudDTOBuilder defaultFraudDTO(){
        return FraudDTO.builder()
                    .withCreatedAt(LocalDateTime.now())
                    .withTotalAmount(BigDecimal.valueOf(1500))
                    .withTotalDiscount(BigDecimal.valueOf(500))
                    .withCustomerId(UUID.randomUUID())
                    .withOrderId(12345L)
                    .withId(UUID.randomUUID().toString());
    }
 }
