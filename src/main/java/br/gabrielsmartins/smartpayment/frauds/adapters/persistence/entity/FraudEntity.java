package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudEntity {

    private UUID id;
    private Long orderId;
    private UUID customerId;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final List<FraudItemEntity> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Map<PaymentMethodData, BigDecimal> paymentMethods = new LinkedHashMap<>();

    public List<FraudItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Map<PaymentMethodData, BigDecimal> getPaymentMethods() {
        return Collections.unmodifiableMap(paymentMethods);
    }

    public Integer addItem(FraudItemEntity item){
        item.getId().setFraud(this);
        this.items.add(item);
        return items.size();
    }

    public Integer addPaymentMethod(PaymentMethodData paymentMethod, BigDecimal amount){
        this.paymentMethods.put(paymentMethod, amount);
        return paymentMethods.size();
    }
}
