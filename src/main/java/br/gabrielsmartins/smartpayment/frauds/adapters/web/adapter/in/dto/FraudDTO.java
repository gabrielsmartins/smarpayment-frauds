package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto;

import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("customer_id")
    private UUID customerId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("total_discount")
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonProperty("items")
    private final List<FraudItemDTO> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonProperty("payment_methods")
    private final Map<PaymentMethod, BigDecimal> paymentMethods = new LinkedHashMap<>();


    public List<FraudItemDTO> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Map<PaymentMethod, BigDecimal> getPaymentMethods() {
        return Collections.unmodifiableMap(paymentMethods);
    }

    public Integer addItem(FraudItemDTO item){
        this.items.add(item);
        return items.size();
    }

    public Integer addPaymentMethod(PaymentMethod paymentMethod, BigDecimal amount){
        this.paymentMethods.put(paymentMethod, amount);
        return paymentMethods.size();
    }
}
