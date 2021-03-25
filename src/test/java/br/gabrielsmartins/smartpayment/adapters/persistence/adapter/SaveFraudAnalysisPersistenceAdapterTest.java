package br.gabrielsmartins.smartpayment.adapters.persistence.adapter;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.FraudItemPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.FraudPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.mapper.PaymentMethodPersistenceMapper;
import br.gabrielsmartins.smartpayment.adapters.persistence.service.SaveFraudPersistenceService;
import br.gabrielsmartins.smartpayment.adapters.persistence.support.FraudEntitySupport;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.application.support.FraudSupport.defaultFraud;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveFraudAnalysisPersistenceAdapterTest {

    private SaveFraudPersistenceAdapter adapter;
    private SaveFraudPersistenceService service;

    @BeforeEach
    public void setup(){
        this.service = mock(SaveFraudPersistenceService.class);
        var itemPersistenceMapper = new FraudItemPersistenceMapper();
        var paymentMethodPersistenceMapper = new PaymentMethodPersistenceMapper();
        FraudPersistenceMapper mapper = new FraudPersistenceMapper(itemPersistenceMapper, paymentMethodPersistenceMapper);
        this.adapter = new SaveFraudPersistenceAdapter(service, mapper);
    }

    @Test
    @DisplayName("Given Fraud When Save Then Return Saved Fraud")
    public void givenFraudWhenSaveThenReturnSavedFraud(){
        FraudAnalysis fraudAnalysis = defaultFraud().build();

        when(service.save(any(FraudEntity.class))).thenReturn(Mono.just(FraudEntitySupport.defaultFraudEntity().build()));

        this.adapter.save(fraudAnalysis)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }
}
