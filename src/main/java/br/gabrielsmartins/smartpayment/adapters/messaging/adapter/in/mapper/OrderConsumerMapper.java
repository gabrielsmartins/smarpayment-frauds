package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.mapper;

import br.gabrielsmartins.schemas.order_received.OrderReceived;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderConsumerMapper {

    private final OrderItemConsumerMapper itemConsumerMapper;
    private final PaymentMethodConsumerMapper paymentMethodConsumerMapper;

    public Order mapToDomain(OrderReceived message){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderReceived, Order>() {
            @Override
            protected void configure() {
                using((Converter<String, UUID>) context -> {
                    String source = context.getSource();
                    return source == null ? null : UUID.fromString(source);
                }).map(source.getCustomerId(), destination.getCustomerId());
            }
        });
        Order order = mapper.map(message, Order.class);
        message.getItems()
               .stream()
               .map(itemConsumerMapper::mapToDomain)
               .forEach(order::addItem);
        message.getPaymentMethods()
               .stream()
               .map(paymentMethodConsumerMapper::mapToDomain)
               .forEach(it -> order.addPaymentMethod(it.getKey(), it.getValue()));
        return order;
    }

}
