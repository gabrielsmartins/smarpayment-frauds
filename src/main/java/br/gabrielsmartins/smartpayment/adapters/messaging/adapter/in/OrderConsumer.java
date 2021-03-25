package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in;

import br.gabrielsmartins.schemas.order_received.OrderReceived;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.mapper.OrderConsumerMapper;
import br.gabrielsmartins.smartpayment.application.ports.in.ProcessOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final ProcessOrderUseCase useCase;
    private final OrderConsumerMapper mapper;

    @Bean
    public Consumer<Flux<Message<OrderReceived>>> consume(){
        return payload -> {
            payload.doOnNext(message -> log.info("Receiving order: {}", message))
                   .map(Message::getPayload)
                   .map(this.mapper::mapToDomain)
                   .subscribe(order -> {
                       useCase.process(order)
                              .doOnSuccess(result -> log.info("Order processed successfully: {}", result))
                              .doOnError(error -> log.error("Order processing failed", error));
                   });
        };
    }
}
