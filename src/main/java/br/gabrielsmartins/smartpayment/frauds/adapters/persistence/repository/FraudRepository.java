package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;


import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.FraudEntityRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FraudRepository {

    private final DatabaseClient client;
    private final FraudEntityRowMapper mapper;
    private final FraudItemRepository fraudItemRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public Mono<FraudEntity> save(FraudEntity fraudEntity) {
        return this.client.sql("INSERT INTO tbl_fraud (order_id,customer_id,created_at,total_amount,total_discount) " +
                               "VALUES (:pOrderId,:pCustomerId,:pCreatedAt,:pTotalAmount,:pTotalDiscount)")
                               .bind("pOrderId", fraudEntity.getOrderId())
                               .bind("pCustomerId", fraudEntity.getCustomerId())
                               .bind("pCreatedAt", fraudEntity.getCreatedAt())
                               .bind("pTotalAmount", fraudEntity.getTotalAmount())
                               .bind("pTotalDiscount", fraudEntity.getTotalDiscount())
                               .filter((statement, executeFunction) -> statement.returnGeneratedValues("fraud_id").execute())
                               .fetch()
                               .one()
                               .map(r ->  r.get("fraud_id"))
                               .cast(UUID.class)
                               .flatMap(id -> {
                                    fraudEntity.setId(id);
                                    return Mono.just(fraudEntity);
                               })
                               .then(this.paymentMethodRepository.saveAll(fraudEntity.getPaymentMethods()).then())
                               .then(this.fraudItemRepository.saveAll(fraudEntity.getItems()).then())
                               .thenReturn(fraudEntity)
                               .doOnSuccess(f -> log.info("Fraud saved successfully: {}", f))
                               .doOnError(e -> log.error("Error saving fraud", e));
    }

    public Mono<FraudEntity> findById(UUID id) {
         return this.client.sql("SELECT * FROM tbl_fraud " +
                                "WHERE fraud_id = :pFraudId")
                                .bind("pFraudId", id)
                                .map(mapper::apply)
                                .one();
    }

    public Flux<FraudEntity> findAll() {
        return this.client.sql("SELECT * FROM tbl_fraud")
                .map(mapper::apply)
                .all();
    }

    public Mono<FraudEntity> findByOrderId(Long orderId) {
        return this.client.sql("SELECT * FROM tbl_fraud " +
                               "WHERE order_id = :pOrderId")
                          .bind("pOrderId", orderId)
                          .map(mapper::apply)
                          .one();
    }



    public Flux<FraudEntity> findByProductId(UUID productId, Pageable pageable) {
        return this.client.sql("SELECT DISTINCT F.* FROM tbl_fraud F " +
                               "INNER JOIN tbl_fraud_items FI " +
                                     "ON F.fraud_id = FI.fraud_id " +
                               "WHERE FI.product_id = :pProductId")
                .bind("pProductId", productId)
                .map(mapper::apply)
                .all();
    }

    public Flux<FraudEntity> findByCustomerId(UUID customerId, Pageable pageable) {
        return this.client.sql("SELECT * FROM tbl_fraud " +
                               "WHERE customer_id = :pCustomerId")
                          .bind("pCustomerId",customerId)
                          .map(mapper::apply)
                          .all();
    }

    public Flux<FraudEntity> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable) {
        return this.client.sql("SELECT * FROM tbl_fraud " +
                               "WHERE created_at BETWEEN :pStartDatetime AND :pEndDatetime")
                          .bind("pStartDatetime",startDatetime)
                          .bind("pEndDatetime",endDatetime)
                          .map(mapper::apply)
                          .all();
    }

}
