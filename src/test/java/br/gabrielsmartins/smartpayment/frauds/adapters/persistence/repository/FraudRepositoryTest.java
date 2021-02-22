package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.config.DatabaseConfiguration;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.FraudEntityMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.FraudItemEntityMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.PaymentMethodDataMapper;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.DatabaseConfigSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support.FraudEntitySupport.defaultFraud;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataR2dbcTest
@Import({FraudRepository.class,
        FraudItemRepository.class,
        DatabaseConfiguration.class,
        DatabaseConfigSupport.class,
        FraudEntityMapper.class,
        FraudItemEntityMapper.class,
        PaymentMethodDataMapper.class,
        PaymentMethodRepository.class,
        PaymentMethodDataMapper.class})
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FraudRepositoryTest {

    @Autowired
    private FraudRepository repository;

    @Autowired
    private FraudItemRepository fraudItemRepository;

    private FraudEntity fraudEntity;
    private FraudItemEntity itemEntity;

    @BeforeEach
    public void setup(){
        this.fraudEntity = defaultFraud()
                .withId(null)
                .build();
        this.itemEntity = FraudItemEntity.builder()
                                        .withId(FraudItemEntity.FraudItemEntityId.builder()
                                                .withProductId(UUID.randomUUID())
                                                .build())
                                        .withQuantity(1)
                                        .withAmount(BigDecimal.valueOf(1500.50))
                                        .build();
        this.fraudEntity.addItem(itemEntity);
        this.fraudEntity.addPaymentMethod(PaymentMethodData.CASH, BigDecimal.valueOf(1000));
        this.fraudEntity.addPaymentMethod(PaymentMethodData.CREDIT_CARD, BigDecimal.valueOf(500));
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
        this.repository.save(fraudEntity).subscribe();
        this.repository.findById(fraudEntity.getId())
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull())
                       .verifyComplete();
    }

    @Test
    @DisplayName("Given Frauds When Exists Then Return Fraud List")
    public void givenFraudsWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).subscribe();
        this.repository.findAll()
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull())
                       .verifyComplete();
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Fraud List")
    public void givenOrderIdWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).subscribe();
        this.repository.findByOrderId(fraudEntity.getOrderId())
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull())
                       .verifyComplete();
    }

    @Test
    @DisplayName("Given Customer Id When Exists Then Return Fraud List")
    public void givenCustomerIdWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).subscribe();
        this.repository.findByCustomerId(fraudEntity.getCustomerId(), PageRequest.of(0,30))
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull())
                       .verifyComplete();
    }

    @Test
    @DisplayName("Given Product Id When Exists Then Return Fraud List")
    public void givenProductIdWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).subscribe();
        this.repository.findByProductId(itemEntity.getId().getProductId(), PageRequest.of(0, 30))
                       .as(StepVerifier::create)
                       .expectNextCount(1)
                       .verifyComplete();
    }


    @Test
    @DisplayName("Given Interval When Exists Then Return Fraud List")
    public void givenIntervalWhenExistsThenReturnFraudList(){
        this.repository.save(fraudEntity).subscribe();
        this.repository.findByInterval(fraudEntity.getCreatedAt().minusDays(1), fraudEntity.getCreatedAt().plusDays(1), PageRequest.of(0,30))
                       .as(StepVerifier::create)
                       .assertNext(f -> assertThat(f).isNotNull())
                       .verifyComplete();
    }

}
