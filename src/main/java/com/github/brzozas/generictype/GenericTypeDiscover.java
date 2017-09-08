package com.github.brzozas.generictype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;

public final class GenericTypeDiscover {

    private GenericTypeDiscover() {
    }

    public static <T, O> Class<T> forInterface(final Class<O> ifaceClass, final O implementator, final int typeIndex) {
        Type[] interfacesTypes = implementator.getClass().getGenericInterfaces();
        Optional<Type> iface = Arrays.stream(interfacesTypes)
                .filter(i -> i.getTypeName().startsWith(ifaceClass.getName()))
                .findFirst();
        if (iface.isPresent()) {
            return (Class<T>) (((ParameterizedType) iface.get()).getActualTypeArguments()[typeIndex]);
        }
        throw new IllegalStateException("Can't discover generic type");
    }

    public static <T> Class<T> forClass(final Class<?> clazz, final int typeIndex) {
        return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[typeIndex];
    }
}
