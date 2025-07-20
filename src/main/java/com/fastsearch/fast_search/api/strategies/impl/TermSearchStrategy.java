package com.fastsearch.fast_search.api.strategies.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import com.fastsearch.fast_search.api.requests.impl.SingleValueSearchRequest;
import com.fastsearch.fast_search.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

public class TermSearchStrategy<T, SR extends SingleValueSearchRequest> implements SearchStrategy<T, SR> {

    /*
     * Term Query, belirlenen alan üzerinde tam eşleşen belgeleri arar.
     * */

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        String field = request.getField();
        Object value = request.getValue();
        FieldValue fieldValue = FieldValue.of(value);

        Query query = NativeQuery.builder()
                .withQuery(q ->
                        q.term(t -> t
                                .field(field)
                                .value(fieldValue)
                        )
                ).build();

        return getHits(query, operations, entityClass);
    }
}
