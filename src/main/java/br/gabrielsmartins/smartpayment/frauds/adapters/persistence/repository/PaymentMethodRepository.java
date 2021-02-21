package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper.PaymentMethodDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentMethodRepository implements ReactiveRepository<>{

    private final DatabaseClient client;
    private final PaymentMethodDataMapper mapper;

    public Flux<Map<PaymentMethodData, BigDecimal>> saveAll(UUID fraudId, Map<PaymentMethodData, BigDecimal>  paymentMethods){
        return this.client.inConnectionMany(connection -> {
            var statement = connection.createStatement("INSERT INTO tbl_fraud_payment_methods (fraud_id,payment_method_id,payment_method_amount) VALUES ($1,$2,$3)");
            paymentMethods.forEach((key, value) -> statement
                    .bind(0, fraudId)
                    .bind(1, key)
                    .bind(2, value)
                    .add());
             return Flux.from(statement.execute()).flatMap(result -> result.map(mapper::apply));
        });
    }
}
