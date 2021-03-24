package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.config.DatabaseConfiguration;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudEntitySupport.defaultFraudEntity;
import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudItemEntitySupport.defaultFraudItemEntity;
import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.PaymentMethodEntitySupport.defaultPaymentMethodEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@DataMongoTest
@ActiveProfiles("test")
@Import(DatabaseConfiguration.class)
public class FraudAnalysisRepositoryTest {

    @Autowired
    private FraudRepository repository;

    private FraudEntity fraudEntity;
    private FraudItemEntity itemEntity;

    @BeforeEach
    public void setup(){
        this.fraudEntity = defaultFraudEntity()
                .withId(null)
                .build();
        this.itemEntity = defaultFraudItemEntity().build();
        this.fraudEntity.addItem(itemEntity);

        PaymentMethodEntity paymentMethod1 = defaultPaymentMethodEntity().withPaymentMethod(PaymentMethodData.CASH)
                                                                         .build();

        PaymentMethodEntity paymentMethod2 = defaultPaymentMethodEntity().withPaymentMethod(PaymentMethodData.CREDIT_CARD)
                                                                         .build();

        this.fraudEntity.addPaymentMethod(paymentMethod1);
        this.fraudEntity.addPaymentMethod(paymentMethod2);
    }

    @Test
    @DisplayName("Given Fraud When Save Then Return Saved Fraud")
    public void givenFraudWhenSaveThenReturnSavedFraud(){
        this.repository.save(fraudEntity)
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull())
                       .verifyComplete();
    }


    @Test
    @DisplayName("Given Id When Exists Then Return Fraud")
    public void givenIdWhenExistsThenReturnFraud(){
        this.repository.save(fraudEntity).block();
        this.repository.findById(fraudEntity.getId())
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull());
    }

    @Test
    @DisplayName("Given Frauds When Exists Then Return Fraud List")
    public void givenFraudsWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).block();
        this.repository.findAll()
                       .as(StepVerifier::create)
                       .expectNextCount(1)
                       .assertNext(f -> assertThat(f).isNotNull());
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Fraud List")
    public void givenOrderIdWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).block();
        this.repository.findByOrderId(fraudEntity.getOrderId())
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull());
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Fraud List")
    public void givenCustomerIdWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).block();
        this.repository.findByCustomerId(fraudEntity.getCustomerId(), PageRequest.of(0,30))
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull())
                       .verifyComplete();
    }

    @Test
    @DisplayName("Given Product Id When Exists Then Return Fraud List")
    public void givenProductIdWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).block();
        this.repository.findByProductId(itemEntity.getProductId(), PageRequest.of(0, 30))
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull());
    }


    @Test
    @DisplayName("Given Interval When Exists Then Return Fraud List")
    public void givenIntervalWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).block();
        this.repository.findByInterval(fraudEntity.getCreatedAt().minusDays(1), fraudEntity.getCreatedAt().plusDays(1), PageRequest.of(0,30))
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull());
    }

}
