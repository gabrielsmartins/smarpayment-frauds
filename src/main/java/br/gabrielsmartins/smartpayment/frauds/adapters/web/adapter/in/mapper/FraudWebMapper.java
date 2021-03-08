package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.FraudDTO;
import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.dto.PaymentMethodDTO;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FraudWebMapper {

    private final FraudItemWebMapper itemWebMapper;

    public Fraud mapToDomain(FraudDTO fraudDTO) {
        var mapper = new ModelMapper();
        Fraud fraud = mapper.map(fraudDTO, Fraud.class);
        fraud.setCreatedAt(LocalDateTime.now());
        fraudDTO.getItems().stream().map(itemWebMapper::mapToDomain).forEach(fraud::addItem);
        fraudDTO.getPaymentMethods()
                .forEach(paymentMethodDTO -> fraud.addPaymentMethod(PaymentMethod.valueOf(paymentMethodDTO.getPaymentMethod()), paymentMethodDTO.getAmount()));
        return fraud;
    }

    public FraudDTO mapToDTO(Fraud fraud){
        var mapper = new ModelMapper();
        FraudDTO fraudDTO = mapper.map(fraud, FraudDTO.class);
        fraud.getItems().stream().map(itemWebMapper::mapToDTO).forEach(fraudDTO::addItem);
        fraud.getPaymentMethods().forEach((paymentMethod, amount) -> fraudDTO.addPaymentMethod(PaymentMethodDTO.builder()
                                                                                              .withPaymentMethod(paymentMethod.name())
                                                                                              .withAmount(amount)
                                                                                              .build()));
        return fraudDTO;
    }


}
