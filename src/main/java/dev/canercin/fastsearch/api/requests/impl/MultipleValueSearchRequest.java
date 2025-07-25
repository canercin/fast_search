package dev.canercin.fastsearch.api.requests.impl;

import dev.canercin.fastsearch.api.requests.SearchRequest;

import java.util.Set;

/**
 * Represents a search request that allows searching for multiple values in a single field.
 * This class is typically used for scenarios where you want to find documents that match any of the
 * specified values in a particular field.
 */
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
