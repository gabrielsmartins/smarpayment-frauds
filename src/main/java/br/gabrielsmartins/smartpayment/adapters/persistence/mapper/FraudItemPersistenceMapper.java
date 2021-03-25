package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysisItem;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudItemEntity;
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
