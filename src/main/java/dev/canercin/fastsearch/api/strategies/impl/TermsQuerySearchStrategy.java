package dev.canercin.fastsearch.api.strategies.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import dev.canercin.fastsearch.api.requests.impl.MultipleValueSearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;
import java.util.Set;

/**
 * @param <T> Generic type for the search results.
 * @param <SR> The type of the search request, which extends MultipleValueSearchRequest.
 */
public class TermsQuerySearchStrategy<T, SR extends MultipleValueSearchRequest> implements SearchStrategy<T, SR> {

    /**
     * @param operations Elasticsearch operations to be used for the search.
     * @param entityClass The class of the entity to be searched.
     * @param request The search request containing the parameters for the search.
     * @return A list of search results of type T.
     */
    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        String field = request.getField();
        Set<Object> valueList = request.getValues();
        List<FieldValue> fieldValues = valueList.stream().map(FieldValue::of).toList();

        Query query = NativeQuery.builder()
                .withQuery(q ->
                        q.terms(ts -> ts
                                .field(field)
                                .terms(terms -> terms
                                        .value(fieldValues)
                                )
                        )
                )
                .build();

        return getHits(query, operations, entityClass);
    }
}
