package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraudPersistenceMapper {

    private final FraudItemPersistenceMapper itemPersistenceMapper;
    private final PaymentMethodPersistenceMapper paymentMethodPersistenceMapper;

    public FraudEntity mapToEntity(Fraud fraud) {
        var mapper = new ModelMapper();
        FraudEntity fraudEntity = mapper.map(fraud, FraudEntity.class);
        fraud.getItems()
             .stream()
             .map(itemPersistenceMapper::mapToEntity)
             .forEach(fraudEntity::addItem);
        fraud.getPaymentMethods()
             .entrySet()
             .stream()
             .map(paymentMethodPersistenceMapper::mapToEntity)
             .forEach(fraudEntity::addPaymentMethod);
        return fraudEntity;
    }

    public Fraud mapToDomain(FraudEntity fraudEntity) {
        var mapper = new ModelMapper();
        Fraud fraud = mapper.map(fraudEntity, Fraud.class);
        fraudEntity.getItems()
                .stream()
                .map(itemPersistenceMapper::mapToDomain)
                .forEach(fraud::addItem);
        fraudEntity.getPaymentMethods()
                .stream()
                .map(paymentMethodPersistenceMapper::mapToDomain)
                .forEach(entry -> fraud.addPaymentMethod(entry.getKey(), entry.getValue()));
        return fraud;
    }

}
