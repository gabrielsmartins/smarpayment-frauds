package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudItemDTO {

    @JsonProperty(value = "product_id", required = true)
    @NotNull
    private UUID productId;

    @JsonProperty(value = "quantity", required = true)
    @NotNull
    private Integer quantity;

    @JsonProperty(value = "amount", required = true)
    @NotNull
    private BigDecimal amount;

}
