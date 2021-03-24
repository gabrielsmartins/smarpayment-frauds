package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.NotifyOrderValidationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudSupport.defaultFraud;
import static org.mockito.Mockito.*;

public class NotifyFraudAnalysisServiceTest {

    private NotifyFraudAnalysisService service;
    private NotifyOrderValidationPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(NotifyOrderValidationPort.class);
        this.service = new NotifyFraudAnalysisService(port);
    }

    @Test
    @DisplayName("Given Fraud Analysis When Notify Then Return Notified Fraud")
    public void givenFraudAnalysisWhenNotifyThenReturnNotifiedFraud(){
        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(this.port.notify(any(FraudAnalysis.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.notify(fraudAnalysis);

        verify(this.port, times(1)).notify(any(FraudAnalysis.class));
    }


}
