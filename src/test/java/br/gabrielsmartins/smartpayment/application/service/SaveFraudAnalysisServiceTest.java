package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.application.ports.out.SaveFraudPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.application.support.FraudSupport.defaultFraud;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SaveFraudAnalysisServiceTest {

    private SaveFraudAnalysisService service;
    private SaveFraudPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SaveFraudPort.class);
        this.service = new SaveFraudAnalysisService(this.port);
    }

    @Test
    @DisplayName("Given Fraud When Save Then Return Saved Fraud")
    public void givenFraudWhenSaveThenReturnSavedFraud(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(this.port.save(any(FraudAnalysis.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.save(fraudAnalysis)
                     .as(StepVerifier::create)
                     .expectNextCount(1)
                     .verifyComplete();

        verify(this.port, times(1)).save(any(FraudAnalysis.class));
    }
}
