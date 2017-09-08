package com.github.brzozas.reflection.method.valueconverter;

public class ToStringConverter implements ValueConverter<String> {

    @Override
    public String convert(final Object value) {
        return value.toString();
    }
}
