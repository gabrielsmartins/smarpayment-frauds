package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.ports.out.NotifyOrderValidationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static br.gabrielsmartins.smartpayment.frauds.application.support.OrderSupport.defaultOrder;
import static org.mockito.Mockito.*;

public class NotifyOrderValidationServiceTest {

    private NotifyOrderValidationService service;
    private NotifyOrderValidationPort port;

    @BeforeEach
    public void setup(){
        this.port = mock(NotifyOrderValidationPort.class);
        this.service = new NotifyOrderValidationService(port);
    }

    @Test
    @DisplayName("Given Order When Notify Then Return Notified Order")
    public void givenOrderWhenNotifyThenReturnNotifiedOrder(){
        Order order = defaultOrder().build();

        when(this.port.notify(any(Order.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.notify(order);

        verify(this.port, times(1)).notify(any(Order.class));
    }
}
