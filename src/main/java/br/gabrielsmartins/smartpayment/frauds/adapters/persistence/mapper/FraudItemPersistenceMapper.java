package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class FraudItemPersistenceMapper {

    public FraudItemEntity mapToEntity(FraudItem fraudItem){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<FraudItem, FraudItemEntity>() {
                        @Override
                        protected void configure() {
                           skip(destination.getId().getFraud());
                        }
                    });
        return mapper.map(fraudItem, FraudItemEntity.class);
    }

    public FraudItem mapToDomain(FraudItemEntity fraudItemEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<FraudItemEntity, FraudItem>() {
            @Override
            protected void configure() {
                skip(destination.getId().getFraud());
            }
        });
        return mapper.map(fraudItemEntity, FraudItem.class);
    }
}
