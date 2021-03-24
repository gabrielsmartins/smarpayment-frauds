package br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in;

import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper.FraudItemWebMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.web.adapter.in.mapper.FraudWebMapper;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
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
@WebFluxTest(controllers = SearchFraudController.class)
@Import({FraudWebMapper.class, FraudItemWebMapper.class})
public class SearchFraudAnalysisControllerTest {

    @MockBean
    private SearchFraudUseCase useCase;

    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("Given Frauds When Exists Then Return Fraud")
    public void givenFraudsWhenExistsThenReturnFraud(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(useCase.findAll()).thenReturn(Flux.just(fraudAnalysis));

        webClient.get()
                .uri("/frauds-v1/frauds")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].id", fraudAnalysis.getId());

        verify(this.useCase, times(1)).findAll();
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Fraud")
    public void givenIdWhenExistsThenReturnFraud(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(useCase.findById(anyString())).thenReturn(Mono.just(fraudAnalysis));

        webClient.get()
                 .uri("/frauds-v1/frauds/{id}", fraudAnalysis.getId())
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody()
                 .jsonPath("id", fraudAnalysis.getId());

        verify(this.useCase, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Fraud")
    public void givenOrderIdWhenExistsThenReturnFraud(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(useCase.findByOrderId(anyLong())).thenReturn(Mono.just(fraudAnalysis));

        webClient.get()
                 .uri(uriBuilder -> uriBuilder.path("/frauds-v1/frauds")
                         .queryParam("order_id", fraudAnalysis.getOrderId())
                     .build())
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody()
                 .jsonPath("id", fraudAnalysis.getId());

        verify(this.useCase, times(1)).findByOrderId(anyLong());
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Fraud")
    public void givenCustomerIdWhenExistsThenReturnFraud(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(useCase.findByCustomerId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(fraudAnalysis));

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/frauds-v1/frauds")
                        .queryParam("customer_id", fraudAnalysis.getCustomerId())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].id", fraudAnalysis.getId());

        verify(this.useCase, times(1)).findByCustomerId(any(UUID.class), any(Pageable.class));
    }

    @Test
    @DisplayName("Given Product Id When Exists Then Return Fraud")
    public void givenProductIdWhenExistsThenReturnFraud(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(useCase.findByProductId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(fraudAnalysis));

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/frauds-v1/frauds")
                        .queryParam("product_id", fraudAnalysis.getCustomerId())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].id", fraudAnalysis.getId());

        verify(this.useCase, times(1)).findByProductId(any(UUID.class), any(Pageable.class));
    }

    @Test
    @DisplayName("Given Interval When Exists Then Return Fraud")
    public void givenIntervalWhenExistsThenReturnFraud(){

        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(useCase.findByInterval(any(LocalDateTime.class),any(LocalDateTime.class), any(Pageable.class))).thenReturn(Flux.just(fraudAnalysis));

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/frauds-v1/frauds")
                        .queryParam("start_date_time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .queryParam("end_date_time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("[0].id", fraudAnalysis.getId());

        verify(this.useCase, times(1)).findByInterval(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class));
    }
}
