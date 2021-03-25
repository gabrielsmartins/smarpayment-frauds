package br.gabrielsmartins.smartpayment.adapters.web.support;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.FraudItemDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class FraudItemDTOSupport {

    private FraudItemDTOSupport(){
        super();
    }

    public static FraudItemDTO.FraudItemDTOBuilder defaultFraudItemDTO(){
        return FraudItemDTO.builder()
                              .withProductId(UUID.randomUUID())
                              .withQuantity(1)
                              .withAmount(BigDecimal.valueOf(500));
    }
}
