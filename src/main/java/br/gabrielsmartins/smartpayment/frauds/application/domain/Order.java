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
public class Order {

    private Long id;
    private UUID customerId;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;
    private boolean fraud;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final List<OrderItem> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Map<PaymentMethod, BigDecimal> paymentMethods = new LinkedHashMap<>();

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Map<PaymentMethod, BigDecimal> getPaymentMethods() {
        return Collections.unmodifiableMap(paymentMethods);
    }

    public Integer addItem(OrderItem item){
        item.setOrder(this);
        this.items.add(item);
        return this.items.size();
    }

    public Integer addPaymentMethod(PaymentMethod paymentMethod, BigDecimal amount){
        this.paymentMethods.put(paymentMethod, amount);
        return this.paymentMethods.size();
    }
}
