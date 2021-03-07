package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.PaymentMethodDataRowMapper;
import io.r2dbc.spi.Statement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentMethodRepository {

    private final DatabaseClient client;
    private final PaymentMethodDataRowMapper mapper;

    public Mono<PaymentMethodEntity> save(PaymentMethodEntity  paymentMethodEntity){
        return this.client.sql("INSERT INTO tbl_fraud_payment_methods (fraud_id,payment_method_id,payment_method_amount) " +
                                                                        "VALUES (:pFraudId,:pPaymentMethodId,:pAmount)")
                                                                        .bind("pFraudId", paymentMethodEntity.getId().getFraud().getId())
                                                                        .bind("pPaymentMethodId", paymentMethodEntity.getId().getPaymentMethod().getCode())
                                                                        .bind("pAmount", paymentMethodEntity.getAmount())
                                                                        .fetch()
                                                                        .one()
                                                                        .thenReturn(paymentMethodEntity)
                                                                        .doOnSuccess(pm -> log.info("Payment method saved successfully: {}", pm))
                                                                        .doOnError(e -> log.error("Error saving payment method", e));
    }

    @Transactional
    public Flux<PaymentMethodEntity> saveAll(List<PaymentMethodEntity> paymentMethodEntities){
        return this.client.inConnectionMany(connection -> {
            Statement statement = connection.createStatement("INSERT INTO tbl_fraud_payment_methods (fraud_id,payment_method_id,payment_method_amount) " +
                                                                 "VALUES ($1,$2,$3)");

            paymentMethodEntities.forEach(paymentMethodEntity -> {
                statement.bind(0, paymentMethodEntity.getId().getFraud().getId())
                         .bind(1, paymentMethodEntity.getId().getPaymentMethod().getCode())
                         .bind(2, paymentMethodEntity.getAmount())
                         .add();
            });
            return Flux.from(statement.execute()).flatMap(result -> result.map(this.mapper::apply));
        });
    }

    public Flux<PaymentMethodEntity> findByFraudId(UUID fraudId){
        return this.client.sql("SELECT * FROM tbl_fraud_payment_methods " +
                                "WHERE fraud_id = :pFraud_id")
                                .bind("pFraud_id", fraudId)
                                .map(mapper::apply)
                                .all();
    }
}
