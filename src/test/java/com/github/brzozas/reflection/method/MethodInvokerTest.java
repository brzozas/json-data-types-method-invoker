package com.github.brzozas.reflection.method;

import com.github.brzozas.reflection.method.valueconverter.ValueConverter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MethodInvokerTest {

    private MethodInvoker objectUnderTest;

    @Test
    public void invoke_shouldInvokeSingleParamMethod() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //given
        final String value = "10";
        ValueConverter valueConverter = mock(ValueConverter.class);
        when(valueConverter.isAplicableForType(Long.class)).thenReturn(true);
        when(valueConverter.convert(value)).thenReturn(Long.parseLong(value));
        objectUnderTest = new MethodInvoker(Collections.singletonList(valueConverter));
        DestinationObject destinationObject = new DestinationObject();

        //when
        objectUnderTest.invoke(destinationObject, "setLongValue", value);

        //then
        assertEquals(Long.valueOf(value), destinationObject.getLongValue());
    }

    @Test
    public void invoke_shouldInvokeMultiParamMethod() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //given
        final String longValue = "10";
        ValueConverter longValueConverter = mock(ValueConverter.class);
        when(longValueConverter.isAplicableForType(Long.class)).thenReturn(true);
        when(longValueConverter.convert(longValue)).thenReturn(Long.parseLong(longValue));

        final String stringValue = "20";
        ValueConverter stringValueConverter = mock(ValueConverter.class);
        when(longValueConverter.isAplicableForType(String.class)).thenReturn(true);
        when(longValueConverter.convert(stringValue)).thenReturn(stringValue);

        objectUnderTest = new MethodInvoker(Lists.newArrayList(longValueConverter, stringValueConverter));
        DestinationObject destinationObject = new DestinationObject();

        //when
        objectUnderTest.invoke(destinationObject, "setStringAndLongValue", stringValue, longValue);

        //then
        assertEquals(Long.valueOf(longValue), destinationObject.getLongValue());
        assertEquals(stringValue, destinationObject.getStringValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invoke_shouldThrowIllegalArgumentExceptionForClassWithOverloadMethodsWithEqualsParametersCount()
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //given
        objectUnderTest = new MethodInvoker(Lists.newArrayList(mock(ValueConverter.class)));
        InvalidDestinationObject destinationObject = new InvalidDestinationObject();

        //when
        objectUnderTest.invoke(destinationObject, "setValue", "");

        //then
        //IllegalArgumentException expected
    }

    @Test(expected = IllegalArgumentException.class)
    public void invoke_shouldThrowIllegalArgumentExceptionWhenThereIsNoSuitableValueConverter()
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //given
        //given
        final String value = "10";
        ValueConverter valueConverter = mock(ValueConverter.class);
        when(valueConverter.isAplicableForType(Integer.class)).thenReturn(true);
        objectUnderTest = new MethodInvoker(Collections.singletonList(valueConverter));
        DestinationObject destinationObject = new DestinationObject();

        //when
        objectUnderTest.invoke(destinationObject, "setLongValue", value);

        //when
        objectUnderTest.invoke(destinationObject, "setValue", "");

        //then
        //IllegalArgumentException expected
    }
}
