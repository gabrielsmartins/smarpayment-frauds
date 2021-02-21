package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraudWebMapper {

    private final FraudItemWebMapper itemWebMapper;

    public FraudDTO mapToDTO(Fraud fraud){
        var mapper = new ModelMapper();
        FraudDTO fraudDTO = mapper.map(fraud, FraudDTO.class);
        fraud.getItems().stream().map(itemWebMapper::mapToDTO).forEach(fraudDTO::addItem);
        fraud.getPaymentMethods().forEach(fraudDTO::addPaymentMethod);
        return fraudDTO;
    }
}
