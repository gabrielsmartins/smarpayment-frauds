package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudItemDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudItemWebMapper {

    public FraudItemDTO mapToDTO(FraudItem fraudItem){
        var mapper = new ModelMapper();
        return mapper.map(fraudItem, FraudItemDTO.class);
    }
}
