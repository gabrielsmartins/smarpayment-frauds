package br.gabrielsmartins.smartpayment.application.domain;

import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudAnalysis {

    private String id;
    private Long orderId;
    private UUID customerId;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;
    private boolean fraud;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final List<FraudAnalysisItem> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Map<PaymentMethod, BigDecimal> paymentMethods = new LinkedHashMap<>();


    public List<FraudAnalysisItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Map<PaymentMethod, BigDecimal> getPaymentMethods() {
        return Collections.unmodifiableMap(paymentMethods);
    }

    public Integer addItem(FraudAnalysisItem item){
        item.setFraudAnalysis(this);
        this.items.add(item);
        return items.size();
    }

    public Integer addPaymentMethod(PaymentMethod paymentMethod, BigDecimal amount){
        this.paymentMethods.put(paymentMethod, amount);
        return paymentMethods.size();
    }
}
