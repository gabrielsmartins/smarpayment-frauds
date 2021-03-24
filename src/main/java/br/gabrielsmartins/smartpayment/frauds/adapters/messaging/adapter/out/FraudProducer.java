package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out;

import br.gabrielsmartins.schemas.fraud_detected.FraudDetected;
import br.gabrielsmartins.schemas.fraud_discarded.FraudDiscarded;
import br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper.FraudProducerMapper;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.NotifyOrderValidationPort;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
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
public class FraudProducer implements NotifyOrderValidationPort {

    private final FraudProducerMapper mapper;
    private final Sinks.Many<Message<? extends SpecificRecord>> buffer = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public Mono<FraudAnalysis> notify(FraudAnalysis fraudAnalysis) {
        if(fraudAnalysis.isFraud())
            return notifyFraudDetected(fraudAnalysis);
        return notifyFraudDiscarded(fraudAnalysis);
    }

    private Mono<FraudAnalysis> notifyFraudDetected(FraudAnalysis fraudAnalysis) {
        FraudDetected payload = mapper.mapToFraudDetectedMessage(fraudAnalysis);
        Message<FraudDetected> message = MessageBuilder.withPayload(payload)
                                                       .build();
        buffer.tryEmitNext(message);
        return Mono.just(fraudAnalysis);
    }

    private Mono<FraudAnalysis> notifyFraudDiscarded(FraudAnalysis fraudAnalysis) {
        FraudDiscarded payload = mapper.mapToFraudDiscardedMessage(fraudAnalysis);
        Message<FraudDiscarded> message = MessageBuilder.withPayload(payload)
                                                        .build();
        buffer.tryEmitNext(message);
        return Mono.just(fraudAnalysis);
    }

    @Bean
    public Supplier<Flux<Message<? extends SpecificRecord>>> produce() {
        return buffer::asFlux;
    }
}
