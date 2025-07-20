package dev.canercin.fastsearch.api.strategies.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import dev.canercin.fastsearch.api.requests.impl.SingleValueSearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

public class MatchQueryStrategy<T, SR extends SingleValueSearchRequest> implements SearchStrategy<T, SR> {

    /*
     * Match Query, belirlenen alan üzerinde kısmı eşleşme araması yapar.
     * */

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        String field = request.getField();
        Object value = request.getValue();
        FieldValue fieldValue = FieldValue.of(value);

        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .match(m -> m
                                .field(field)
                                .query(fieldValue)
                        )
                ).build();

        return getHits(query, operations, entityClass);
    }
}
