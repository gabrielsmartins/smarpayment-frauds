package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper.FraudItemWebMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper.FraudWebMapper;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.SearchFraudUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static br.gabrielsmartins.smartpayment.frauds.application.support.FraudSupport.defaultFraud;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FraudController.class)
@Import({FraudWebMapper.class, FraudItemWebMapper.class})
public class FraudControllerTest {

    @MockBean
    private SearchFraudUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("Given Frauds When Exists Then Return Fraud")
    public void givenFraudsWhenExistsThenReturnFraud(){

        Fraud fraud = defaultFraud().build();

        when(useCase.findAll()).thenReturn(Flux.just(fraud));

        webClient.get()
                .uri("/frauds")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].id", fraud.getId());

        verify(this.useCase, times(1)).findAll();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Fraud")
    public void givenIdWhenExistsThenReturnFraud(){

        Fraud fraud = defaultFraud().build();

        when(useCase.findById(any(UUID.class))).thenReturn(Mono.just(fraud));

        webClient.get()
                 .uri("/frauds/{id}", fraud.getId())
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody()
                 .jsonPath("id", fraud.getId());

        verify(this.useCase, times(1)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Fraud")
    public void givenOrderIdWhenExistsThenReturnFraud(){

        Fraud fraud = defaultFraud().build();

        when(useCase.findByOrderId(anyLong())).thenReturn(Mono.just(fraud));

        webClient.get()
                 .uri(uriBuilder -> uriBuilder.path("/frauds")
                         .queryParam("order_id", fraud.getOrderId())
                     .build())
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody()
                 .jsonPath("id", fraud.getId());

        verify(this.useCase, times(1)).findByOrderId(anyLong());
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Fraud")
    public void givenCustomerIdWhenExistsThenReturnFraud(){

        Fraud fraud = defaultFraud().build();

        when(useCase.findByCustomerId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(fraud));

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/frauds")
                        .queryParam("customer_id", fraud.getCustomerId())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].id", fraud.getId());

        verify(this.useCase, times(1)).findByCustomerId(any(UUID.class), any(Pageable.class));
    }

    @Test
    @DisplayName("Given Product Id When Exists Then Return Fraud")
    public void givenProductIdWhenExistsThenReturnFraud(){

        Fraud fraud = defaultFraud().build();

        when(useCase.findByProductId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(fraud));

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/frauds")
                        .queryParam("product_id", fraud.getCustomerId())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].id", fraud.getId());

        verify(this.useCase, times(1)).findByProductId(any(UUID.class), any(Pageable.class));
    }

    @Test
    @DisplayName("Given Interval When Exists Then Return Fraud")
    public void givenIntervalWhenExistsThenReturnFraud(){

        Fraud fraud = defaultFraud().build();

        when(useCase.findByInterval(any(LocalDateTime.class),any(LocalDateTime.class), any(Pageable.class))).thenReturn(Flux.just(fraud));

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/frauds")
                        .queryParam("start_date_time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .queryParam("end_date_time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].id", fraud.getId());

        verify(this.useCase, times(1)).findByInterval(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class));
    }
}
