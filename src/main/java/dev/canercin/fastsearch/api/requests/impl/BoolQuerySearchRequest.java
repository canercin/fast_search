package dev.canercin.fastsearch.api.requests.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import dev.canercin.fastsearch.api.requests.SearchRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoolQuerySearchRequest implements SearchRequest {
    private Map<String, List<Query>> queries = new HashMap<>();

    public BoolQuerySearchRequest() {
        queries.put("must", new ArrayList<>());
        queries.put("should", new ArrayList<>());
        queries.put("must_not", new ArrayList<>());
        queries.put("filter", new ArrayList<>());
    }

    public Map<String, List<Query>> getQueries() {
        return queries;
    }

    public void setQueries(Map<String, List<Query>> queries) {
        this.queries = queries;
    }

    /**
     * @param type can be "must", "should", "must_not", or "filter"
     * */
    public List<Query> getQueriesByType(String type) {
        return queries.get(type);
    }

    /**
     * @param type can be "must", "should", "must_not", or "filter"
     * */
    public void addQuery(String type, List<Query> queries) {
        getQueriesByType(type).addAll(queries);
    }
}
