package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.schemas.order_validated.Item;
import br.gabrielsmartins.schemas.order_validated.OrderValidated;
import br.gabrielsmartins.schemas.order_validated.PaymentMethod;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderProducerMapper {

    private final OrderItemProducerMapper itemProducerMapper;
    private final PaymentMethodProducerMapper paymentMethodProducerMapper;

    public OrderValidated mapToMessage(Order order){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Order, OrderValidated>() {

            @Override
            protected void configure() {
              map(destination.getValidatedAt()).setValidatedAt(LocalDateTime.now());
            }

        });
        List<Item> itemsMessage = order.getItems().stream().map(itemProducerMapper::mapToMessage).collect(Collectors.toList());
        List<PaymentMethod> paymentMethodsMessage = order.getPaymentMethods().entrySet()
                                                                             .stream()
                                                                             .map(it -> paymentMethodProducerMapper.mapToMessage(it.getKey(), it.getValue()))
                                                                             .collect(Collectors.toList());
        OrderValidated orderValidated = mapper.map(order, OrderValidated.class);
        orderValidated.setItems(itemsMessage);
        orderValidated.setPaymentMethods(paymentMethodsMessage);
        return orderValidated;
    }
}
