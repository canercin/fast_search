package dev.canercin.fastsearch.api.requests.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import dev.canercin.fastsearch.api.requests.SearchRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a search request that uses a boolean query structure.
 * This class allows for the construction of complex queries by combining multiple query types
 * such as "must", "should", "must_not", and "filter".
 */
public class BoolQuerySearchRequest implements SearchRequest {
    private Map<String, List<Query>> queries = new HashMap<>();

    public BoolQuerySearchRequest() {
        queries.put("must", new ArrayList<>());
        queries.put("should", new ArrayList<>());
        queries.put("must_not", new ArrayList<>());
        queries.put("filter", new ArrayList<>());
    }

    /**
     * @return A map containing lists of queries categorized by their types.
     */
    public Map<String, List<Query>> getQueries() {
        return queries;
    }

    /**
     * @param queries A map containing lists of queries categorized by their types.
     * This method allows setting the queries for the boolean search request.
     */
    public void setQueries(Map<String, List<Query>> queries) {
        this.queries = queries;
    }

    /**
     * @param type can be "must", "should", "must_not", or "filter"
     * */
    public List<Query> getQueriesByType(String type) {
        checkKey(type);
        return queries.get(type);
    }

    /**
     * @param type can be "must", "should", "must_not", or "filter"
     * */
    public void addQuery(String type, List<Query> queries) {
        getQueriesByType(type).addAll(queries);
    }

    private void checkKey(String key) {
        List<String> keys = List.of("must", "should", "must_not", "filter");

        if (StringUtils.isBlank(key) || keys.contains(key))
        {
            throw new IllegalArgumentException("Not valid key: " + key);
        }
    }
}
