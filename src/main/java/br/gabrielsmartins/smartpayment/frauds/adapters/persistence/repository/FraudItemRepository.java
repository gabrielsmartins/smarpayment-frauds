package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity.FraudItemEntityId;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.FraudItemEntityMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FraudItemRepository extends ReactiveRepository<FraudItemEntity, FraudItemEntityId> {

    public FraudItemRepository(DatabaseClient client, FraudItemEntityMapper mapper) {
        super(client, mapper);
    }

    @Override
    protected String getTableName() {
        return "tbl_fraud_items";
    }

    @Override
    protected List<String> getFieldsId() {
        return List.of("fraud_id","product_id");
    }

    @Override
    protected List<String> getInsertFields() {
        return List.of("fraud_id","product_id","item_quantity","item_amount");
    }

    @Override
    protected List<String> getSelectFields() {
        return List.of("fraud_id","product_id","item_quantity","item_amount");
    }
}
