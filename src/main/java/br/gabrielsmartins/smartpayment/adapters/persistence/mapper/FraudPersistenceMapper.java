package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraudPersistenceMapper {

    private final FraudItemPersistenceMapper itemPersistenceMapper;
    private final PaymentMethodPersistenceMapper paymentMethodPersistenceMapper;

    public FraudEntity mapToEntity(FraudAnalysis fraudAnalysis) {
        var mapper = new ModelMapper();
        FraudEntity fraudEntity = mapper.map(fraudAnalysis, FraudEntity.class);
        fraudAnalysis.getItems()
             .stream()
             .map(itemPersistenceMapper::mapToEntity)
             .forEach(fraudEntity::addItem);
        fraudAnalysis.getPaymentMethods()
             .entrySet()
             .stream()
             .map(paymentMethodPersistenceMapper::mapToEntity)
             .forEach(fraudEntity::addPaymentMethod);
        return fraudEntity;
    }

    public FraudAnalysis mapToDomain(FraudEntity fraudEntity) {
        var mapper = new ModelMapper();
        FraudAnalysis fraudAnalysis = mapper.map(fraudEntity, FraudAnalysis.class);
        fraudEntity.getItems()
                .stream()
                .map(itemPersistenceMapper::mapToDomain)
                .forEach(fraudAnalysis::addItem);
        fraudEntity.getPaymentMethods()
                .stream()
                .map(paymentMethodPersistenceMapper::mapToDomain)
                .forEach(entry -> fraudAnalysis.addPaymentMethod(entry.getKey(), entry.getValue()));
        return fraudAnalysis;
    }

}
