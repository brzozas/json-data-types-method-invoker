package com.github.brzozas.reflection.method.valueconverter;

public class ToLongConverter implements ValueConverter<Long> {

    @Override
    public Long convert(final Object value) {
        return Long.valueOf(value.toString());
    }
}
