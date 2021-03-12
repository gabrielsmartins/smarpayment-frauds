package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper.FraudItemPersistenceMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper.FraudPersistenceMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper.PaymentMethodPersistenceMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service.SearchFraudPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudEntitySupport.defaultFraudEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class SearchFraudPersistenceAdapterTest {

    private SearchFraudPersistenceAdapter adapter;
    private SearchFraudPersistenceService service;
    
    @BeforeEach
    public void setup(){
        this.service = mock(SearchFraudPersistenceService.class);
        var itemPersistenceMapper = new FraudItemPersistenceMapper();
        var paymentMethodPersistenceMapper = new PaymentMethodPersistenceMapper();
        var mapper = new FraudPersistenceMapper(itemPersistenceMapper, paymentMethodPersistenceMapper);
        this.adapter = new SearchFraudPersistenceAdapter(service, mapper);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Fraud")
    public void givenIdWhenExistsThenReturnFraud(){

        when(this.service.findById(anyString())).thenReturn(Mono.just(defaultFraudEntity().build()));

        this.adapter.findById(UUID.randomUUID().toString())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.service, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("Given Frauds When Exists Then Return Fraud List")
    public void givenFraudsWhenExistsThenReturnFraudList(){

        when(this.service.findAll()).thenReturn(Flux.just(defaultFraudEntity().build()));

        this.adapter.findAll()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.service, times(1)).findAll();
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Fraud List")
    public void givenOrderIdWhenExistsThenReturnFraudList(){

        when(this.service.findByOrderId(anyLong())).thenReturn(Mono.just(defaultFraudEntity().build()));

        this.adapter.findByOrderId(12345L)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.service, times(1)).findByOrderId(anyLong());
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Fraud List")
    public void givenCustomerIdWhenExistsThenReturnFraudList(){

        when(this.service.findByCustomerId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraudEntity().build()));

        this.adapter.findByCustomerId(UUID.randomUUID(), PageRequest.of(0,30))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.service, times(1)).findByCustomerId(any(UUID.class), any(Pageable.class));
    }

    @Test
    @DisplayName("Given Product Id When Exists Then Return Fraud List")
    public void givenProductIdWhenExistsThenReturnFraudList(){

        when(this.service.findByProductId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraudEntity().build()));

        this.adapter.findByProductId(UUID.randomUUID(), PageRequest.of(0,30))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.service, times(1)).findByProductId(any(UUID.class), any(Pageable.class));
    }


    @Test
    @DisplayName("Given Interval When Exists Then Return Fraud List")
    public void givenIntervalWhenExistsThenReturnFraudList(){

        when(this.service.findByInterval(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraudEntity().build()));

        this.adapter.findByInterval(LocalDateTime.now(), LocalDateTime.now(), PageRequest.of(0,30))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.service, times(1)).findByInterval(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class));
    }
}
