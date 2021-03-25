package br.gabrielsmartins.smartpayment.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.adapters.persistence.repository.FraudRepository;
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

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.FraudEntitySupport.defaultFraudEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class SearchFraudAnalysisPersistenceServiceTest {
    
    private SearchFraudPersistenceService service;
    private FraudRepository repository;

    
    @BeforeEach
    public void setup(){
        this.repository = mock(FraudRepository.class);
        this.service = new SearchFraudPersistenceService(repository);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Fraud")
    public void givenIdWhenExistsThenReturnFraud(){

        when(this.repository.findById(anyString())).thenReturn(Mono.just(defaultFraudEntity().build()));

        this.service.findById(UUID.randomUUID().toString())
                            .as(StepVerifier::create)
                            .expectNextCount(1)
                            .verifyComplete();

        verify(this.repository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("Given Frauds When Exists Then Return Fraud List")
    public void givenFraudsWhenExistsThenReturnFraudList(){

        when(this.repository.findAll()).thenReturn(Flux.just(defaultFraudEntity().build()));

        this.service.findAll()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Fraud List")
    public void givenOrderIdWhenExistsThenReturnFraudList(){

        when(this.repository.findByOrderId(anyLong())).thenReturn(Mono.just(defaultFraudEntity().build()));

        this.service.findByOrderId(12345L)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.repository, times(1)).findByOrderId(anyLong());
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Fraud List")
    public void givenCustomerIdWhenExistsThenReturnFraudList(){

        when(this.repository.findByCustomerId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraudEntity().build()));

        this.service.findByCustomerId(UUID.randomUUID(), PageRequest.of(0,30))
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.repository, times(1)).findByCustomerId(any(UUID.class), any(Pageable.class));
    }

    @Test
    @DisplayName("Given Product Id When Exists Then Return Fraud List")
    public void givenProductIdWhenExistsThenReturnFraudList(){

        when(this.repository.findByProductId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraudEntity().build()));

        this.service.findByProductId(UUID.randomUUID(), PageRequest.of(0,30))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.repository, times(1)).findByProductId(any(UUID.class), any(Pageable.class));
    }


    @Test
    @DisplayName("Given Interval When Exists Then Return Fraud List")
    public void givenIntervalWhenExistsThenReturnFraudList(){

        when(this.repository.findByInterval(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraudEntity().build()));

        this.service.findByInterval(LocalDateTime.now(), LocalDateTime.now(), PageRequest.of(0,30))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.repository, times(1)).findByInterval(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class));
    }
}
