package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysisItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudItemPersistenceMapper {

    public FraudItemEntity mapToEntity(FraudAnalysisItem fraudAnalysisItem){
        var mapper = new ModelMapper();
        return mapper.map(fraudAnalysisItem, FraudItemEntity.class);
    }

    public FraudAnalysisItem mapToDomain(FraudItemEntity fraudItemEntity){
        var mapper = new ModelMapper();
        return mapper.map(fraudItemEntity, FraudAnalysisItem.class);
    }
}
