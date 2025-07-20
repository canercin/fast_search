package dev.canercin.fastsearch.api.strategies.impl;

import dev.canercin.fastsearch.api.requests.impl.SingleValueSearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;
import java.time.Instant;

public class MatchAllSearchStrategy<T, SR extends SingleValueSearchRequest> implements SearchStrategy<T, SR> {

    /**
     * Match All Query, bütün dokümanları eşleştirir.
     * */

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
