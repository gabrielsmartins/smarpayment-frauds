package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;


import br.gabrielsmartins.schemas.fraud_detected.Item;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysisItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudItemProducerMapper {

    public Item mapToFraudDetectedMessage(FraudAnalysisItem item){
        var mapper = new ModelMapper();
        return mapper.map(item, Item.class);
    }

    public br.gabrielsmartins.schemas.fraud_discarded.Item mapToFraudDiscardedMessage(FraudAnalysisItem item){
        var mapper = new ModelMapper();
        return mapper.map(item, br.gabrielsmartins.schemas.fraud_discarded.Item.class);
    }

}
