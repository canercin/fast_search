package dev.canercin.fastsearch.api.context;

import dev.canercin.fastsearch.api.requests.SearchRequest;
import dev.canercin.fastsearch.api.strategies.SearchStrategy;
import dev.canercin.fastsearch.api.strategies.impl.*;
import dev.canercin.fastsearch.api.type.SearchType;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  Caner ÇİN
 * @version 1.0.0
 * @param <T> Generic type for the search results.
 */
public class SearchContext<T> {
    private final Map<SearchType, SearchStrategy<T, ? extends SearchRequest>> strategyMap = new HashMap<>();

    public SearchContext() {
        strategyMap.put(SearchType.BOOL_QUERY, new BoolQueryStrategy<>());
        strategyMap.put(SearchType.DATE_RANGE, new DateRangeQueryStrategy<>());
        strategyMap.put(SearchType.MATCH_ALL, new MatchAllSearchStrategy<>());
        strategyMap.put(SearchType.MATCH, new MatchQueryStrategy<>());
        strategyMap.put(SearchType.MULTI_MATCH, new MultiMatchSearchStrategy<>());
        strategyMap.put(SearchType.NUMERIC_RANGE, new NumberRangeQueryStrategy<>());
        strategyMap.put(SearchType.TERM, new TermSearchStrategy<>());
        strategyMap.put(SearchType.TERMS, new TermsQuerySearchStrategy<>());
    }

    /**
     * @param type The type of search to be performed.
     * @param operations Elasticsearch operations to be used for the search.
     * @param entityClass The class of the entity to be searched.
     * @param request The search request containing the parameters for the search.
     * @param <SR> The type of the search request.
     * @return A list of search results of type T.
     */
    public <SR extends SearchRequest> List<T> search(SearchType type, ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        @SuppressWarnings("unchecked")
        final SearchStrategy<T, SR> strategy = (SearchStrategy<T, SR>) strategyMap.get(type);
        return strategy.search(operations, entityClass, request);
    }
}
