package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudDTO;
import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.PaymentMethodDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FraudWebMapper {

    private final FraudItemWebMapper itemWebMapper;

    public FraudAnalysis mapToDomain(FraudDTO fraudDTO) {
        var mapper = new ModelMapper();
        FraudAnalysis fraudAnalysis = mapper.map(fraudDTO, FraudAnalysis.class);
        fraudAnalysis.setCreatedAt(LocalDateTime.now());
        fraudDTO.getItems().stream().map(itemWebMapper::mapToDomain).forEach(fraudAnalysis::addItem);
        fraudDTO.getPaymentMethods()
                .forEach(paymentMethodDTO -> fraudAnalysis.addPaymentMethod(PaymentMethod.valueOf(paymentMethodDTO.getPaymentMethod()), paymentMethodDTO.getAmount()));
        return fraudAnalysis;
    }

    public FraudDTO mapToDTO(FraudAnalysis fraudAnalysis){
        var mapper = new ModelMapper();
        FraudDTO fraudDTO = mapper.map(fraudAnalysis, FraudDTO.class);
        fraudAnalysis.getItems().stream().map(itemWebMapper::mapToDTO).forEach(fraudDTO::addItem);
        fraudAnalysis.getPaymentMethods().forEach((paymentMethod, amount) -> fraudDTO.addPaymentMethod(PaymentMethodDTO.builder()
                                                                                              .withPaymentMethod(paymentMethod.name())
                                                                                              .withAmount(amount)
                                                                                              .build()));
        return fraudDTO;
    }


}
