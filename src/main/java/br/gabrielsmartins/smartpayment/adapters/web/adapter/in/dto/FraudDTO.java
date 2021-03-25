package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty(value = "order_id", required = true)
    @NotNull
    private Long orderId;

    @JsonProperty(value = "customer_id", required = true)
    @NotNull
    private UUID customerId;

    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty(value = "total_amount", required = true)
    @NotNull
    private BigDecimal totalAmount;

    @JsonProperty(value = "total_discount", required = true)
    @NotNull
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonProperty(value = "items", required = true)
    @NotEmpty
    private final List<@Valid FraudItemDTO> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonProperty(value = "payment_methods")
    private final List<@Valid PaymentMethodDTO> paymentMethods = new LinkedList<>();


    public List<FraudItemDTO> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<PaymentMethodDTO> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addItem(FraudItemDTO item){
        this.items.add(item);
        return items.size();
    }

    public Integer addPaymentMethod(PaymentMethodDTO paymentMethod){
        this.paymentMethods.add(paymentMethod);
        return paymentMethods.size();
    }
}
