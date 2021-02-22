package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.PaymentMethodDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentMethodRepository {

    private final DatabaseClient client;
    private final PaymentMethodDataMapper mapper;

    @Transactional
    public Mono<Map.Entry<PaymentMethodData, BigDecimal>> save(UUID fraudId, Map.Entry<PaymentMethodData, BigDecimal>  paymentMethod){
        doInsert(fraudId,paymentMethod);
        return Mono.just(paymentMethod);
    }

    private Mono<Map<String, Object>> doInsert(UUID fraudId, Map.Entry<PaymentMethodData, BigDecimal>  paymentMethod) {
        return this.client.sql("INSERT INTO tbl_fraud_payment_methods (fraud_id,payment_method_id,payment_method_amount) " +
                        "VALUES (:pFraudId,:pPaymentMethodId,:pAmount)")
                   .bind("pFraudId", fraudId)
                   .bind("pPaymentMethodId", paymentMethod.getKey().getCode())
                   .bind("pAmount", paymentMethod.getValue())
                   .fetch()
                   .one();
    }

    @Transactional
    public Flux<Map<PaymentMethodData, BigDecimal>> saveAll(UUID fraudId, Map<PaymentMethodData, BigDecimal>  paymentMethods){
        paymentMethods.forEach((k,v) -> doInsert(fraudId, Map.entry(k,v)).block());
        return Flux.just(paymentMethods);
    }
}
