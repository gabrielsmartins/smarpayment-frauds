package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.SaveFraudUseCase;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.SaveFraudPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class SaveFraudService implements SaveFraudUseCase {

    private final SaveFraudPort port;

    @Override
    public Mono<Fraud> save(Fraud fraud) {
        log.info("Saving fraud: {}", fraud);
        return this.port.save(fraud);
    }

}
