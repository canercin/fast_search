package dev.canercin.fastsearch.api.strategies.impl;

import dev.canercin.fastsearch.api.requests.impl.MultiMatchSearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

public class MultiMatchSearchStrategy<T, SR extends MultiMatchSearchRequest> implements SearchStrategy<T, SR> {

    /*
     * Multi Match Query, birden fazla alan üzerinde eşleşme araması yapar.
     * */

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        List<String> fields = request.getFields();
        String value = request.getValue();

        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .multiMatch(mm -> mm
                                .fields(fields)
                                .query(value)
                        )
                ).build();

        return getHits(query, operations, entityClass);
    }
}
