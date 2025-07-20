package dev.canercin.fastsearch.api.requests.impl;

import dev.canercin.fastsearch.api.requests.SearchRequest;

public class SingleValueSearchRequest implements SearchRequest {
    private String field;
    private Object value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
