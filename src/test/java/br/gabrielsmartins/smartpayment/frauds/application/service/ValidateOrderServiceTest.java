package br.gabrielsmartins.smartpayment.frauds.application.service;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static br.gabrielsmartins.smartpayment.frauds.application.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidateOrderServiceTest {

    private ValidateOrderService service;

    @BeforeEach
    public void setup(){
        this.service = new ValidateOrderService();
    }

    @Test
    @DisplayName("Given Order When Total Discount Is Greater Then Total Amount Then Return True")
    public void givenOrderWhenTotalDiscountIsGreaterThenTotalAmountThenReturnTrue(){

        Order order = defaultOrder()
                      .withTotalDiscount(BigDecimal.valueOf(1500))
                      .withTotalAmount(BigDecimal.valueOf(500))
                      .build();

        this.service.isValid(order)
                     .as(StepVerifier::create)
                     .assertNext(it -> assertThat(it).isTrue())
                     .verifyComplete();

    }
}
