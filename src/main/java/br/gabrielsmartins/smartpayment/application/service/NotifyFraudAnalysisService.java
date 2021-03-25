package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.ports.in.NotifyOrderValidationUseCase;
import br.gabrielsmartins.smartpayment.application.ports.out.NotifyOrderValidationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotifyFraudAnalysisService implements NotifyOrderValidationUseCase {

    private final NotifyOrderValidationPort port;

    @Override
    public Mono<FraudAnalysis> notify(FraudAnalysis fraudAnalysis){
        return this.port.notify(fraudAnalysis);
    }

}
