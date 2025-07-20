package dev.canercin.fastsearch.api.requests.impl;

import dev.canercin.fastsearch.api.requests.SearchRequest;

import java.util.Set;

public class MultipleValueSearchRequest implements SearchRequest {
    private String field;
    private Set<Object> values;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Set<Object> getValues() {
        return values;
    }

    public void setValues(Set<Object> values) {
        this.values = values;
    }
}
