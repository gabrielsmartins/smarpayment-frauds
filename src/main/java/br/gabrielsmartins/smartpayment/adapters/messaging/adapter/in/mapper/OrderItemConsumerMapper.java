package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.mapper;

import br.gabrielsmartins.schemas.order_received.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemConsumerMapper {

    public OrderItem mapToDomain(Item item){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Item, OrderItem>() {
            @Override
            protected void configure() {
                using((Converter<String, UUID>) context -> {
                    String source = context.getSource();
                    return source == null ? null : UUID.fromString(source);
                }).map(source.getProductId(), destination.getProductId());
            }
        });
        return mapper.map(item, OrderItem.class);
    }
}
