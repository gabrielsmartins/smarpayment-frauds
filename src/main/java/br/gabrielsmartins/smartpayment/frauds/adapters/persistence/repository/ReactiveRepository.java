package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.repository;

import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class ReactiveRepository<T, ID>{

    protected final DatabaseClient client;
    protected final BiFunction<Row, Object, T> mapper;

    protected <S extends T> Mono<S> save(S entity){
        List<String> insertFields = getInsertFields();
        String insertFieldsValue = String.join(",", insertFields);
        DatabaseClient.GenericExecuteSpec executeSpec = this.client.sql(String.format("INSERT INTO (%s)", insertFieldsValue));
        insertFields.forEach(field -> executeSpec.bind("",field));
        return (Mono<S>) executeSpec.map(mapper::apply).one();
    }

    protected <S extends T> Flux<S> saveAll(Iterable<S> entities){
        this.client.inConnectionMany(connection -> {
            var statement = connection.createStatement("INSERT INTO tbl_fraud_payment_methods (fraud_id,payment_method_id,payment_method_amount) VALUES ($1,$2,$3)");
            paymentMethods.forEach((key, value) -> statement
                                                    .bind(0, fraudId)
                                                    .bind(1, key)
                                                    .bind(2, value)
                                                    .add());
            return Flux.from(statement.execute()).flatMap(result -> result.map(mapper::apply));
        });
    }

    protected Mono<T> findById(ID id){
        List<String> fieldsId = getFieldsId();
        String fieldsIdValue = String.join(",", fieldsId);
        DatabaseClient.GenericExecuteSpec executeSpec = this.client.sql(String.format("SELECT * FROM WHERE %s", fieldsIdValue));
        fieldsId.forEach(fieldId -> executeSpec.bind("",fieldId));
        return executeSpec.map(mapper::apply).one();
    }

    protected Flux<T> findAll(){
        return this.client.sql(String.format("SELECT * FROM %s", getTableName()))
                          .map(mapper::apply).all();
    }

    protected abstract String getTableName();

    protected abstract List<String> getFieldsId();

    protected abstract List<String> getInsertFields();

}
