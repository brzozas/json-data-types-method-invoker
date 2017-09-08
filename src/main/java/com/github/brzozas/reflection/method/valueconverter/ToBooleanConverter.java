package com.github.brzozas.reflection.method.valueconverter;

public class ToBooleanConverter implements ValueConverter<Boolean> {

    @Override
    public Boolean convert(final Object value) {
        return Boolean.valueOf(value.toString());
    }
}
