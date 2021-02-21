package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudItemDTO {

    @JsonProperty("product_id")
    private UUID productId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("amount")
    private BigDecimal amount;

}
