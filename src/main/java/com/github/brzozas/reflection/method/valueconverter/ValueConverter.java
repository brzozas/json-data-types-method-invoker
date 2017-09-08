package com.github.brzozas.reflection.method.valueconverter;

import com.github.brzozas.generictype.GenericTypeDiscover;

public interface ValueConverter<T> {

    T convert(final Object value);

    default boolean isAplicableForType(final Class clazz) {
        Class destinationType = GenericTypeDiscover.forInterface(ValueConverter.class, this, 0);
        return clazz.equals(destinationType);
    }

}
