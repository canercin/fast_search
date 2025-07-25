package dev.canercin.fastsearch.api.strategies.impl;

import dev.canercin.fastsearch.api.requests.impl.MultiMatchSearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

/**
 * @param <T> Generic type for the search results.
 * @param <SR> The type of the search request, which extends MultiMatchSearchRequest.
 */
public class MultiMatchSearchStrategy<T, SR extends MultiMatchSearchRequest> implements SearchStrategy<T, SR> {

    /**
     * @param operations Elasticsearch operations to be used for the search.
     * @param entityClass The class of the entity to be searched.
     * @param request The search request containing the parameters for the search.
     * @return A list of search results of type T.
     */
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
