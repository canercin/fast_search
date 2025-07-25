package dev.canercin.fastsearch.api.requests.impl;

import dev.canercin.fastsearch.api.requests.SearchRequest;

import java.util.List;

/**
 * Represents a search request that allows searching across multiple fields with a single value.
 * This class is typically used for full-text search scenarios where the same value needs to be matched
 * against multiple fields in the indexed documents.
 */
public class MultiMatchSearchRequest implements SearchRequest {
    private List<String> fields;
    private String value;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
