package com.github.brzozas.reflection.method.valueconverter;

import java.math.BigDecimal;

public class ToBigDecimalConverter implements ValueConverter<BigDecimal> {

    @Override
    public BigDecimal convert(final Object value) {
        return new BigDecimal(value.toString());
    }
}
