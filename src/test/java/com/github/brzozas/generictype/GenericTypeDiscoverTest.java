package com.github.brzozas.generictype;

import org.junit.Assert;
import org.junit.Test;

public class GenericTypeDiscoverTest {

    @Test
    public void interfaceGenericTypeResolveTest() {
        //given
        GenericIfaceImpl genericIfaceImpl = new GenericIfaceImpl();
        //when
        Class genericType = GenericTypeDiscover.forInterface(GenericIface.class, genericIfaceImpl, 0);
        //then
        Assert.assertEquals(genericType, MySimpleType.class);
    }

    @Test
    public void abstractClassGenericTypeResolveTest() {
        //given
        Class implementationClass = GenericAbstractClassImpl.class;
        //when
        Class genericType = GenericTypeDiscover.forClass(implementationClass, 0);
        //then
        Assert.assertEquals(genericType, MySimpleType.class);
    }
}
