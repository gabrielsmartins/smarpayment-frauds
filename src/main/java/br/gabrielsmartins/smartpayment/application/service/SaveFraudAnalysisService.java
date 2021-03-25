package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.ports.in.SaveFraudUseCase;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.ports.out.SaveFraudPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class SaveFraudAnalysisService implements SaveFraudUseCase {

    private final SaveFraudPort port;

    @Override
    public Mono<FraudAnalysis> save(FraudAnalysis fraudAnalysis) {
        log.info("Saving fraud: {}", fraudAnalysis);
        return this.port.save(fraudAnalysis);
    }

}
