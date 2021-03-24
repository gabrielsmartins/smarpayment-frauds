package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document("frauds")
public class FraudEntity {

    @Id
    @Field("fraud_analysis_id")
    private String id;

    @Field("order_id")
    private Long orderId;

    @Field("customer_id")
    private UUID customerId;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("total_amount")
    private BigDecimal totalAmount;

    @Field("total_discount")
    private BigDecimal totalDiscount;

    @Field("fraud")
    private boolean fraud;

    @Field("items")
    @Getter(AccessLevel.NONE)
    @Builder.Default
    private List<FraudItemEntity> items = new LinkedList<>();

    @Field("payment_methods")
    @Getter(AccessLevel.NONE)
    @Builder.Default
    private List<PaymentMethodEntity> paymentMethods = new LinkedList<>();

    public List<FraudItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<PaymentMethodEntity> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addItem(FraudItemEntity item){
        if(this.items == null){
            this.items = new LinkedList<>();
        }
        this.items.add(item);
        return items.size();
    }

    public Integer addPaymentMethod(PaymentMethodEntity paymentMethod){
        if(this.paymentMethods == null){
            this.paymentMethods = new LinkedList<>();
        }
        this.paymentMethods.add(paymentMethod);
        return paymentMethods.size();
    }
}
