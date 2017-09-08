package com.github.brzozas.reflection.method;

public class DestinationObject {

    private Long longValue;

    private String stringValue;

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(final Long value) {
        this.longValue = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringAndLongValue(final String stringValue, final Long longValue) {
        this.stringValue = stringValue;
        this.longValue = longValue;
    }
}
