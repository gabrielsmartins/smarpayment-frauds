package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudItemDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysisItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudItemWebMapper {

    public FraudAnalysisItem mapToDomain(FraudItemDTO fraudItemDTO){
        var mapper = new ModelMapper();
        return mapper.map(fraudItemDTO, FraudAnalysisItem.class);
    }

    public FraudItemDTO mapToDTO(FraudAnalysisItem fraudAnalysisItem){
        var mapper = new ModelMapper();
        return mapper.map(fraudAnalysisItem, FraudItemDTO.class);
    }
}
