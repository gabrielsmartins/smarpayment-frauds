package br.gabrielsmartins.smartpayment.frauds;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class SmartPaymentFraudApplicationTest {

    @Test
    void contextLoads(){

    }
}
