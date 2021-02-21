package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out;

import br.gabrielsmartins.schemas.order_validated.OrderValidated;
import br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper.OrderProducerMapper;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.NotifyOrderValidationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class OrderProducer implements NotifyOrderValidationPort {

    private final OrderProducerMapper mapper;
    private final Sinks.Many<Message<OrderValidated>> buffer = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public Mono<Order> notify(Order order) {
        OrderValidated payload = mapper.mapToMessage(order);
        Message<OrderValidated> message = MessageBuilder.withPayload(payload)
                                                        .build();
        buffer.tryEmitNext(message);
        return Mono.just(order);
    }

    @Bean
    public Supplier<Flux<Message<OrderValidated>>> produce() {
        return buffer::asFlux;
    }
}
