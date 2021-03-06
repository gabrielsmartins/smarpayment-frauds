package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.SaveFraudPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudSupport.defaultFraud;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SaveFraudServiceTest {

    private SaveFraudService service;
    private SaveFraudPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(SaveFraudPort.class);
        this.service = new SaveFraudService(this.port);
    }

    @Test
    @DisplayName("Given Fraud When Save Then Return Saved Fraud")
    public void givenFraudWhenSaveThenReturnSavedFraud(){

        Fraud fraud = defaultFraud().build();

        when(this.port.save(any(Fraud.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.save(fraud)
                     .as(StepVerifier::create)
                     .expectNextCount(1)
                     .verifyComplete();

        verify(this.port, times(1)).save(any(Fraud.class));
    }
}
