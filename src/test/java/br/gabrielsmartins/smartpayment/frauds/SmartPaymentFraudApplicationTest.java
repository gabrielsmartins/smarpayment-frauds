package br.gabrielsmartins.smartpayment.frauds;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS, hierarchyMode = DirtiesContext.HierarchyMode.EXHAUSTIVE)
public class SmartPaymentFraudApplicationTest {

    @Test
    void contextLoads(){

    }
}
