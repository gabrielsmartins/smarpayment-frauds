package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.FraudItemEntityRowMapper;
import io.r2dbc.spi.Statement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FraudItemRepository {

    private final DatabaseClient client;
    private final FraudItemEntityRowMapper mapper;

    @Transactional
    public Mono<FraudItemEntity> save(FraudItemEntity fraudItemEntity) {
        return this.client.sql("INSERT INTO tbl_fraud_items(fraud_id,product_id,item_quantity,item_amount) " +
                                "VALUES(:pFraudId,:pProductId,:pItemQuantity,:pItemAmount)")
                                .bind("pFraudId", fraudItemEntity.getId().getFraud().getId())
                                .bind("pProductId", fraudItemEntity.getId().getProductId())
                                .bind("pItemQuantity", fraudItemEntity.getQuantity())
                                .bind("pItemAmount", fraudItemEntity.getAmount())
                                .fetch()
                                .one()
                                .then(Mono.just(fraudItemEntity));
    }


    @Transactional
    public Flux<FraudItemEntity> saveAll(List<FraudItemEntity> itemEntities) {
       return this.client.inConnectionMany(connection -> {
           Statement statement = connection.createStatement("INSERT INTO tbl_fraud_items(fraud_id,product_id,item_quantity,item_amount) " +
                   "VALUES($1,$2,$3,$4)");

           itemEntities.forEach(item -> {
               statement.bind(0, item.getId().getFraud().getId())
                        .bind(1, item.getId().getProductId())
                        .bind(2, item.getQuantity())
                        .bind(3, item.getAmount())
                        .add();
           });
           return Flux.from(statement.execute()).flatMap(result -> result.map(this.mapper::apply));
       });
    }

}
