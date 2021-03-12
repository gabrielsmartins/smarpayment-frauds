package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudItemPersistenceMapper {

    public FraudItemEntity mapToEntity(FraudItem fraudItem){
        var mapper = new ModelMapper();
        return mapper.map(fraudItem, FraudItemEntity.class);
    }

    public FraudItem mapToDomain(FraudItemEntity fraudItemEntity){
        var mapper = new ModelMapper();
        return mapper.map(fraudItemEntity, FraudItem.class);
    }
}
