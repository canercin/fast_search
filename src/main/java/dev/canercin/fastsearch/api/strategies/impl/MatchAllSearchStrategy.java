package dev.canercin.fastsearch.api.strategies.impl;

import dev.canercin.fastsearch.api.requests.impl.SingleValueSearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;
import java.time.Instant;

/**
 * @param <T> Generic type for the search results.
 * @param <SR> The type of the search request, which extends SingleValueSearchRequest.
 */
public class MatchAllSearchStrategy<T, SR extends SingleValueSearchRequest> implements SearchStrategy<T, SR> {

    /**
     * @param operations Elasticsearch operations to be used for the search.
     * @param entityClass The class of the entity to be searched.
     * @param request The search request containing the parameters for the search.
     * @return A list of search results of type T.
     */
    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {

        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .matchAll(ma -> ma
                                .queryName(String.format("matchAllQuery-%s-%s",
                                        entityClass.getName(), Instant.now())
                                )
                        )
                ).build();

        return getHits(query, operations, entityClass);
    }
}
