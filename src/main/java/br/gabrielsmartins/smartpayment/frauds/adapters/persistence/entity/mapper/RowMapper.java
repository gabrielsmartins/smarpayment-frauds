package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.mapper;

import io.r2dbc.spi.Row;

import java.util.function.BiFunction;

public abstract class RowMapper<E> implements BiFunction<Row, Object, E> {

    protected <T> T parse(Row row, String name, Class<T> clazz) {
        if(row.get(name) == null){
            return null;
        }else{
            return row.get(name, clazz);
        }
    }

}
