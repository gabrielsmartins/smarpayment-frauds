package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.schemas.fraud_detected.FraudDetected;
import br.gabrielsmartins.schemas.fraud_discarded.FraudDiscarded;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FraudProducerMapper {

    private final FraudItemProducerMapper itemProducerMapper;
    private final PaymentMethodProducerMapper paymentMethodProducerMapper;

    public FraudDetected mapToFraudDetectedMessage(FraudAnalysis fraudAnalysis){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<FraudAnalysis, FraudDetected>() {

            @Override
            protected void configure() {
              map(destination.getValidatedAt()).setValidatedAt(LocalDateTime.now());
            }

        });

        var itemsMessage = fraudAnalysis.getItems()
                                                  .stream()
                                                  .map(itemProducerMapper::mapToFraudDetectedMessage)
                                                  .collect(Collectors.toList());

        var paymentMethodsMessage = fraudAnalysis.getPaymentMethods()
                                                                    .entrySet()
                                                                    .stream()
                                                                    .map(it -> paymentMethodProducerMapper.mapToFraudDetectedMessage(it.getKey(), it.getValue()))
                                                                    .collect(Collectors.toList());

        FraudDetected fraudDetected = mapper.map(fraudAnalysis, FraudDetected.class);
        fraudDetected.setItems(itemsMessage);
        fraudDetected.setPaymentMethods(paymentMethodsMessage);
        return fraudDetected;
    }

    public FraudDiscarded mapToFraudDiscardedMessage(FraudAnalysis fraudAnalysis){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<FraudAnalysis, FraudDiscarded>() {

            @Override
            protected void configure() {
                map(destination.getValidatedAt()).setValidatedAt(LocalDateTime.now());
            }

        });

        var itemsMessage = fraudAnalysis.getItems()
                                                  .stream()
                                                  .map(itemProducerMapper::mapToFraudDiscardedMessage)
                                                  .collect(Collectors.toList());

        var paymentMethodsMessage = fraudAnalysis.getPaymentMethods()
                                                                    .entrySet()
                                                                    .stream()
                                                                    .map(it -> paymentMethodProducerMapper.mapToFraudDiscardedMessage(it.getKey(), it.getValue()))
                                                                    .collect(Collectors.toList());

        FraudDiscarded fraudDiscarded = mapper.map(fraudAnalysis, FraudDiscarded.class);
        fraudDiscarded.setItems(itemsMessage);
        fraudDiscarded.setPaymentMethods(paymentMethodsMessage);
        return fraudDiscarded;
    }
}
