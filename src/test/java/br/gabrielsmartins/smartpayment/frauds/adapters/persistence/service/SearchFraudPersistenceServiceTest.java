package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.FraudItemRepository;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.FraudRepository;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.PaymentMethodRepository;
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

import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudEntitySupport.defaultFraud;
import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudItemEntitySupport.defaultFraudItem;
import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.PaymentMethodEntitySupport.defaultPaymentMethod;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class SearchFraudPersistenceServiceTest {
    
    private SearchFraudPersistenceService service;
    private FraudRepository repository;
    private FraudItemRepository fraudItemRepository;
    private PaymentMethodRepository paymentMethodRepository;
    
    @BeforeEach
    public void setup(){
        this.repository = mock(FraudRepository.class);
        this.fraudItemRepository = mock(FraudItemRepository.class);
        this.paymentMethodRepository = mock(PaymentMethodRepository.class);
        this.service = new SearchFraudPersistenceService(repository, fraudItemRepository, paymentMethodRepository);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Fraud")
    public void givenIdWhenExistsThenReturnFraud(){

        when(this.repository.findById(any(UUID.class))).thenReturn(Mono.just(defaultFraud().build()));
        when(this.fraudItemRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultFraudItem().build()));
        when(this.paymentMethodRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultPaymentMethod().build()));

        this.service.findById(UUID.randomUUID())
                            .as(StepVerifier::create)
                            .expectNextCount(1)
                            .verifyComplete();

        verify(this.repository, times(1)).findById(any(UUID.class));
        verify(this.fraudItemRepository, times(1)).findByFraudId(any(UUID.class));
        verify(this.paymentMethodRepository, times(1)).findByFraudId(any(UUID.class));
    }

    @Test
    @DisplayName("Given Frauds When Exists Then Return Fraud List")
    public void givenFraudsWhenExistsThenReturnFraudList(){

        when(this.repository.findAll()).thenReturn(Flux.just(defaultFraud().build()));
        when(this.fraudItemRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultFraudItem().build()));
        when(this.paymentMethodRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultPaymentMethod().build()));

        this.service.findAll()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.repository, times(1)).findAll();
        verify(this.fraudItemRepository, times(1)).findByFraudId(any(UUID.class));
        verify(this.paymentMethodRepository, times(1)).findByFraudId(any(UUID.class));
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Fraud List")
    public void givenOrderIdWhenExistsThenReturnFraudList(){

        when(this.repository.findByOrderId(anyLong())).thenReturn(Mono.just(defaultFraud().build()));
        when(this.fraudItemRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultFraudItem().build()));
        when(this.paymentMethodRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultPaymentMethod().build()));

        this.service.findByOrderId(12345L)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.repository, times(1)).findByOrderId(anyLong());
        verify(this.fraudItemRepository, times(1)).findByFraudId(any(UUID.class));
        verify(this.paymentMethodRepository, times(1)).findByFraudId(any(UUID.class));
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Fraud List")
    public void givenCustomerIdWhenExistsThenReturnFraudList(){

        when(this.repository.findByCustomerId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraud().build()));
        when(this.fraudItemRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultFraudItem().build()));
        when(this.paymentMethodRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultPaymentMethod().build()));

        this.service.findByCustomerId(UUID.randomUUID(), PageRequest.of(0,30))
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.repository, times(1)).findByCustomerId(any(UUID.class), any(Pageable.class));
        verify(this.fraudItemRepository, times(1)).findByFraudId(any(UUID.class));
        verify(this.paymentMethodRepository, times(1)).findByFraudId(any(UUID.class));
    }

    @Test
    @DisplayName("Given Product Id When Exists Then Return Fraud List")
    public void givenProductIdWhenExistsThenReturnFraudList(){

        when(this.repository.findByProductId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraud().build()));
        when(this.fraudItemRepository.findByFraudIdAndProductId(any(UUID.class), any(UUID.class))).thenReturn(Mono.just(defaultFraudItem().build()));
        when(this.paymentMethodRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultPaymentMethod().build()));

        this.service.findByProductId(UUID.randomUUID(), PageRequest.of(0,30))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.repository, times(1)).findByProductId(any(UUID.class), any(Pageable.class));
        verify(this.fraudItemRepository, times(1)).findByFraudIdAndProductId(any(UUID.class), any(UUID.class));
        verify(this.paymentMethodRepository, times(1)).findByFraudId(any(UUID.class));
    }


    @Test
    @DisplayName("Given Interval When Exists Then Return Fraud List")
    public void givenIntervalWhenExistsThenReturnFraudList(){

        when(this.repository.findByInterval(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(Flux.just(defaultFraud().build()));
        when(this.fraudItemRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultFraudItem().build()));
        when(this.paymentMethodRepository.findByFraudId(any(UUID.class))).thenReturn(Flux.just(defaultPaymentMethod().build()));

        this.service.findByInterval(LocalDateTime.now(), LocalDateTime.now(), PageRequest.of(0,30))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        verify(this.repository, times(1)).findByInterval(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class));
        verify(this.fraudItemRepository, times(1)).findByFraudId(any(UUID.class));
        verify(this.paymentMethodRepository, times(1)).findByFraudId(any(UUID.class));
    }
}
