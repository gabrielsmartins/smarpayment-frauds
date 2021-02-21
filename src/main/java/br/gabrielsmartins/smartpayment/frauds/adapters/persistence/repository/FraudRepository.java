package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;


import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Component
public class FraudRepository extends ReactiveRepository<FraudEntity, UUID> {

    private final FraudItemRepository fraudItemRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public FraudRepository(DatabaseClient client, BiFunction<Row, Object, FraudEntity> mapper, FraudItemRepository fraudItemRepository, PaymentMethodRepository paymentMethodRepository) {
        super(client, mapper);
        this.fraudItemRepository = fraudItemRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    @Transactional
    public <S extends FraudEntity> Mono<S> save(S entity) {
        this.template.insert(fraudEntity).block();
        fraudItemRepository.saveAll(fraudEntity.getItems()).subscribe();
        paymentMethodRepository.saveAll(fraudEntity.getId(), fraudEntity.getPaymentMethods());
        return Mono.just(entity);
    }

    @Override
    protected String getTableName() {
        return "tbl_fraud";
    }

    @Override
    protected List<String> getFieldsId() {
        return List.of("fraud_id");
    }

    @Override
    protected List<String> getInsertFields() {
        return List.of("order_id","customer_id","created_at","total_amount","total_discount");
    }

    public Mono<FraudEntity> findByOrderId(Long orderId) {
        return this.client.sql(String.format("SELECT DISTINCT F.*,FI.* FROM %s F " +
                                            "LEFT JOIN %s FI " +
                                                 "ON F.fraud_id = FI.fraud_id" +
                                            "WHERE order_id = :pOrderId", getTableName(), this.fraudItemRepository.getTableName()))
                          .bind("pOrderId", orderId)
                          .map(mapper::apply)
                          .one();
    }

    public Flux<FraudEntity> findByProductId(UUID productId, Pageable pageable) {
        return this.client.sql("SELECT DISTINCT F.*,FI.*,PMF.* " +
                        "FROM tbl_fraud F " +
                        "LEFT JOIN tbl_fraud_items FI " +
                        "ON F.fraud_id = FI.fraud_id " +
                        "INNER JOIN tbl_fraud_payment_methods PMF " +
                        "ON F.fraud_id = PMF.fraud_id"

                //"WHERE FI.product_id = :pProductId"
        )
                //.bind("pProductId", productId)
                .map(mapper::apply)
                .all();
    }

    public Flux<FraudEntity> findByCustomerId(UUID customerId, Pageable pageable) {
        return this.client.sql(String.format("SELECT DISTINCT F.*,FI.* FROM %s F " +
                                             "LEFT JOIN %s FI " +
                                                   "ON F.fraud_id = FI.fraud_id" +
                                             "WHERE customer_id = :pCustomerId", getTableName(), this.fraudItemRepository.getTableName()))
                          .bind("pCustomerId",customerId)
                          .map(mapper::apply)
                          .all();
    }

    public Flux<FraudEntity> findByInterval(LocalDateTime startDatetime, LocalDateTime endDatetime, Pageable pageable) {
        return this.client.sql(String.format("SELECT DISTINCT F.*, FI.* FROM %s F " +
                                            "LEFT JOIN %s FI " +
                                                  "ON F.fraud_id = FI.fraud_id" +
                                             "WHERE created_at BETWEEN :pStartDatetime AND :pEndDatetime " +
                                             "LIMIT ", getTableName(), this.fraudItemRepository.getTableName()))
                          .bind("pStartDatetime",startDatetime)
                          .bind("pEndDatetime",endDatetime)
                          .map(mapper::apply)
                          .all();
    }

}
