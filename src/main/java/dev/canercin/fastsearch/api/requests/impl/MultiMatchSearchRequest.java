package dev.canercin.fastsearch.api.requests.impl;

import dev.canercin.fastsearch.api.requests.SearchRequest;

import java.util.List;

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
