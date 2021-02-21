package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.service;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository.FraudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudEntitySupport.defaultFraud;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveOrderPersistenceServiceTest {

    private SaveFraudPersistenceService service;
    private FraudRepository fraudRepository;

    @BeforeEach
    public void setup(){
        this.fraudRepository = mock(FraudRepository.class);
        this.service = new SaveFraudPersistenceService(fraudRepository);
    }

    @Test
    @DisplayName("Given Fraud When Save Then Return Saved Fraud")
    public void givenFraudWhenSaveThenReturnSavedFraud(){
        FraudEntity fraudEntity = defaultFraud().build();

        when(this.fraudRepository.save(any(FraudEntity.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        this.service.save(fraudEntity)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
    }
}