package com.github.brzozas.reflection.method;

import com.github.brzozas.reflection.method.valueconverter.ValueConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodInvoker {

    private List<ValueConverter> valueConverters;

    public MethodInvoker(final List<ValueConverter> valueConverters) {
        this.valueConverters = valueConverters;
    }

    public Object invoke(final Object object, final String methodName, final Object... values) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = findMethod(object, methodName, values.length);
        return method.invoke(object, convertParamValues(method, values));
    }

    private Method findMethod(final Object object, final String methodName, final int parametersCount) throws NoSuchMethodException {
        List<Method> methods = Arrays.stream(object.getClass().getMethods())
                .filter(m -> m.getName().equals(methodName))
                .filter(m -> m.getParameterCount() == parametersCount).collect(Collectors.toList());
        if (methods.size() == 1) {
            return methods.get(0);
        }
        if (methods.isEmpty()) {
            throw new NoSuchMethodException("Method " + methodName + " with " + parametersCount + " not found");
        }
        throw new IllegalArgumentException("Object with overloaded methods with the same arguments count are not supported");
    }

    private Object convertValue(final Class destinationType, final Object value) {
        for (ValueConverter vc : valueConverters) {
            if (vc.isAplicableForType(destinationType)) {
                return vc.convert(value);
            }
        }
        throw new IllegalArgumentException("No value converter found for type [" + destinationType.getName() + "]");
    }

    private Object[] convertParamValues(final Method method, final Object... values) {
        Class[] paramTypes = method.getParameterTypes();
        Object[] paramsValues = new Object[values.length];
        for(int i=0; i<values.length; i++) {
            paramsValues[i] = convertValue(paramTypes[i], values[i]);
        }
        return paramsValues;
    }

}
