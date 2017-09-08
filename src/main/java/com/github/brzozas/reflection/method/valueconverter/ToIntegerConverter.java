package com.github.brzozas.reflection.method.valueconverter;

public class ToIntegerConverter implements ValueConverter<Integer> {

    @Override
    public Integer convert(final Object value) {
        return Integer.valueOf(value.toString());
    }
}
