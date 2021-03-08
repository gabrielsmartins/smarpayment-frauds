package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {

    @JsonProperty(value = "description", required = true)
    @NotNull
    private String paymentMethod;

    @JsonProperty(value = "amount", required = true)
    @NotNull
    private BigDecimal amount;

}
