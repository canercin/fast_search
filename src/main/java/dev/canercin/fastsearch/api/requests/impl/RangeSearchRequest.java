package dev.canercin.fastsearch.api.requests.impl;

import dev.canercin.fastsearch.api.requests.SearchRequest;

/**
 * Represents a search request that allows searching for documents within a specified range of values.
 * This class is typically used for numeric or date fields where you want to find documents
 * that fall within a certain range defined by the "from" and "to" values.
 */
public class RangeSearchRequest implements SearchRequest {
    private String field;
    private Object from;
    private Object to;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Object getTo() {
        return to;
    }

    public void setTo(Object to) {
        this.to = to;
    }
}
