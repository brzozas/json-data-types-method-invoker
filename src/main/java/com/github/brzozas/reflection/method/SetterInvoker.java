package com.github.brzozas.reflection.method;

import com.github.brzozas.reflection.method.valueconverter.ValueConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SetterInvoker extends MethodInvoker {


    public SetterInvoker(final List<ValueConverter> valueConverters) {
        super(valueConverters);
    }

    public void invoke(final Object object, final String propertyName, final Object value)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String setterMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        super.invoke(object, setterMethodName, value);
    }

}
