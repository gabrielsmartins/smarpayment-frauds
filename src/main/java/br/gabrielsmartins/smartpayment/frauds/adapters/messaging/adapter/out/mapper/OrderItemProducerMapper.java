package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;


import br.gabrielsmartins.schemas.order_validated.Item;
import br.gabrielsmartins.smartpayment.frauds.application.domain.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemProducerMapper {

    public Item mapToMessage(OrderItem item){
        var mapper = new ModelMapper();
        return mapper.map(item, Item.class);
    }
}
