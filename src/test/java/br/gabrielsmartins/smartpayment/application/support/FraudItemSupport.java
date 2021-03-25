package br.gabrielsmartins.smartpayment.application.support;

import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysisItem;
import br.gabrielsmartins.smartpayment.application.domain.FraudAnalysisItem.FraudAnalysisItemBuilder;
import java.math.BigDecimal;
import java.util.UUID;

public class FraudItemSupport {

    private FraudItemSupport(){
        super();
    }

    public static FraudAnalysisItemBuilder defaultFraudItem(){
        return FraudAnalysisItem.builder()
                        .withProductId(UUID.randomUUID())
                        .withQuantity(1)
                        .withAmount(BigDecimal.valueOf(500));
    }
}
