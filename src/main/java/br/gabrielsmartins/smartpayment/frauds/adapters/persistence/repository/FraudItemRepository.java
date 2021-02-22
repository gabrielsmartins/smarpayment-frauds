package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.FraudItemEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FraudItemRepository {

    private final DatabaseClient client;
    private final FraudItemEntityMapper mapper;

    @Transactional
    public Mono<FraudItemEntity> save(FraudItemEntity fraudItemEntity) {
        doInsert(fraudItemEntity);
        return Mono.just(fraudItemEntity);
    }

    private Mono<Map<String,Object>> doInsert(FraudItemEntity fraudItemEntity) {
        return this.client.sql("INSERT INTO tbl_fraud_items (fraud_id,product_id,item_quantity,item_amount) " +
                               "VALUES(:pFraudId,:pProductId,:pItemQuantity,:pItemAmount)")
                               .bind("pFraudId", fraudItemEntity.getId().getFraud().getId())
                               .bind("pProductId", fraudItemEntity.getId().getProductId())
                               .bind("pItemQuantity", fraudItemEntity.getQuantity())
                               .bind("pItemAmount", fraudItemEntity.getAmount())
                               .fetch()
                               .one();
    }

    @Transactional
    public Flux<FraudItemEntity> saveAll(List<FraudItemEntity> itemEntities) {
        itemEntities.forEach(item ->  doInsert(item).block() );
        return Flux.fromIterable(itemEntities);
    }

}
