package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;


import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.FraudEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FraudRepository {

    private final DatabaseClient client;
    private final FraudEntityMapper mapper;
    private final FraudItemRepository fraudItemRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public Mono<FraudEntity> save(FraudEntity fraudEntity) {
        UUID id = this.client.sql("INSERT INTO tbl_fraud (order_id,customer_id,created_at,total_amount,total_discount) " +
                                 "VALUES (:pOrderId,:pCustomerId,:pCreatedAt,:pTotalAmount,:pTotalDiscount)")
                                 .bind("pOrderId", fraudEntity.getOrderId())
                                 .bind("pCustomerId", fraudEntity.getCustomerId())
                                 .bind("pCreatedAt", fraudEntity.getCreatedAt())
                                 .bind("pTotalAmount", fraudEntity.getTotalAmount())
                                 .bind("pTotalDiscount", fraudEntity.getTotalDiscount())
                                 .filter((statement, executeFunction) -> statement.returnGeneratedValues("fraud_id").execute())
                                 .fetch()
                                 .one()
                                 .map(r -> (UUID) r.get("fraud_id"))
                                 .block();
        fraudEntity.setId(id);
        this.fraudItemRepository.saveAll(fraudEntity.getItems()).subscribe();
        this.paymentMethodRepository.saveAll(fraudEntity.getId(), fraudEntity.getPaymentMethods()).subscribe();
        return Mono.just(fraudEntity);
    }

    public Mono<FraudEntity> findById(UUID id) {
        return this.client.sql("SELECT DISTINCT F.*,FI.*,PMF.* FROM tbl_fraud F " +
                                "LEFT JOIN tbl_fraud_items FI " +
                                "ON F.fraud_id = FI.fraud_id " +
                                "LEFT JOIN tbl_fraud_payment_methods PMF " +
                                "ON F.fraud_id = PMF.fraud_id " +
                                "WHERE F.fraud_id = :pFraudId")
                                .bind("pFraudId", id)
                                .map(mapper::apply)
                                .one();
    }

    public Flux<FraudEntity> findAll() {
        return this.client.sql("SELECT DISTINCT F.*,FI.*,PMF.* FROM tbl_fraud F " +
                "LEFT JOIN tbl_fraud_items FI " +
                "ON F.fraud_id = FI.fraud_id " +
                "LEFT JOIN tbl_fraud_payment_methods PMF " +
                "ON F.fraud_id = PMF.fraud_id")
                .map(mapper::apply)
                .all();
    }

    public Mono<FraudEntity> findByOrderId(Long orderId) {
        return this.client.sql("SELECT DISTINCT F.*,FI.* FROM tbl_fraud F " +
                               "LEFT JOIN tbl_fraud_items FI " +
                                    "ON F.fraud_id = FI.fraud_id " +
                               "WHERE order_id = :pOrderId")
                          .bind("pOrderId", orderId)
                          .map(mapper::apply)
                          .one();
    }



    public Flux<FraudEntity> findByProductId(UUID productId, Pageable pageable) {
        return this.client.sql("SELECT DISTINCT F.*,FI.*,PMF.* FROM tbl_fraud F " +
                               "LEFT JOIN tbl_fraud_items FI " +
                                     "ON F.fraud_id = FI.fraud_id " +
                               "LEFT JOIN tbl_fraud_payment_methods PMF " +
                                           "ON F.fraud_id = PMF.fraud_id " +
                               "WHERE FI.product_id = :pProductId")
                .bind("pProductId", productId)
                .map(mapper::apply)
                .all();
    }

    public Flux<FraudEntity> findByCustomerId(UUID customerId, Pageable pageable) {
        return this.client.sql("SELECT DISTINCT F.*,FI.* FROM tbl_fraud F " +
                               "LEFT JOIN tbl_fraud_items FI " +
                                    "ON F.fraud_id = FI.fraud_id " +
                               "WHERE F.customer_id = :pCustomerId")
                          .bind("pCustomerId",customerId)
                          .map(mapper::apply)
                          .all();
    }

    public Flux<FraudEntity> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable) {
        return this.client.sql("SELECT DISTINCT F.*, FI.* FROM tbl_fraud F " +
                               "LEFT JOIN tbl_fraud_items FI " +
                                    "ON F.fraud_id = FI.fraud_id " +
                               "WHERE F.created_at BETWEEN :pStartDatetime AND :pEndDatetime")
                          .bind("pStartDatetime",startDatetime)
                          .bind("pEndDatetime",endDatetime)
                          .map(mapper::apply)
                          .all();
    }

}
