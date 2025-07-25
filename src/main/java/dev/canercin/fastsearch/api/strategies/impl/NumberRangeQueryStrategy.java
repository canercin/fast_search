package dev.canercin.fastsearch.api.strategies.impl;

import dev.canercin.fastsearch.api.requests.impl.RangeSearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

/**
 * @param <T> Generic type for the search results.
 * @param <SR> The type of the search request, which extends RangeSearchRequest.
 */
public class NumberRangeQueryStrategy<T, SR extends RangeSearchRequest> implements SearchStrategy<T, SR> {

    /**
     * @param operations Elasticsearch operations to be used for the search.
     * @param entityClass The class of the entity to be searched.
     * @param request The search request containing the parameters for the search.
     * @return A list of search results of type T.
     */
    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        String field = request.getField();
        String fromValue = request.getFrom().toString();
        String toValue = request.getTo().toString();

        Query query = NativeQuery.builder()
                .withQuery(q ->
                        q.range(r ->
                                r.number(n->
                                        n.field(field)
                                                .gte(Double.parseDouble(fromValue))
                                                .lte(Double.parseDouble(toValue))
                                )
                        )
                )
                .build();

        return getHits(query, operations, entityClass);
    }
}
