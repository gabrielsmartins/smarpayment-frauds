package br.gabrielsmartins.smartpayment.frauds.application.domain;

import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Fraud {

    private UUID id;
    private Long orderId;
    private UUID customerId;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final List<FraudItem> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Map<PaymentMethod, BigDecimal> paymentMethods = new LinkedHashMap<>();


    public List<FraudItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Map<PaymentMethod, BigDecimal> getPaymentMethods() {
        return Collections.unmodifiableMap(paymentMethods);
    }

    public Integer addItem(FraudItem item){
        item.getId().setFraud(this);
        this.items.add(item);
        return items.size();
    }

    public Integer addPaymentMethod(PaymentMethod paymentMethod, BigDecimal amount){
        this.paymentMethods.put(paymentMethod, amount);
        return paymentMethods.size();
    }
}
