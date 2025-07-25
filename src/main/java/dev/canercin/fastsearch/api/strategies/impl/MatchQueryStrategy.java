package dev.canercin.fastsearch.api.strategies.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import dev.canercin.fastsearch.api.requests.impl.SingleValueSearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

/**
 * @param <T> Generic type for the search results.
 * @param <SR> The type of the search request, which extends SingleValueSearchRequest.
 */
public class MatchQueryStrategy<T, SR extends SingleValueSearchRequest> implements SearchStrategy<T, SR> {

    /**
     * @param operations Elasticsearch operations to be used for the search.
     * @param entityClass The class of the entity to be searched.
     * @param request The search request containing the parameters for the search.
     * @return A list of search results of type T.
     */
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
