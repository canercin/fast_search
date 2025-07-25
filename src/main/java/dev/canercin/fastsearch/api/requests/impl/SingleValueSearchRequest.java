package dev.canercin.fastsearch.api.requests.impl;

import dev.canercin.fastsearch.api.requests.SearchRequest;

/**
 * Represents a search request that allows searching for a single value in a specific field.
 * This class is typically used for scenarios where you want to find documents that match a specific value
 * in a particular field, such as an exact match for a keyword or identifier.
 */
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
