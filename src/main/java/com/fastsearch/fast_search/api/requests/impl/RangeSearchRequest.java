package com.fastsearch.fast_search.api.requests.impl;

import com.fastsearch.fast_search.api.requests.SearchRequest;

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
