package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity;

import lombok.*;

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
    private final List<PaymentMethodEntity> paymentMethods = new LinkedList<>();

    public List<FraudItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<PaymentMethodEntity> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addItem(FraudItemEntity item){
        item.getId().setFraud(this);
        this.items.add(item);
        return items.size();
    }

    public Integer addPaymentMethod(PaymentMethodEntity paymentMethod){
        paymentMethod.getId().setFraud(this);
        this.paymentMethods.add(paymentMethod);
        return paymentMethods.size();
    }
}
