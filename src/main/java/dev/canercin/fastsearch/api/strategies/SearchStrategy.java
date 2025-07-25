package dev.canercin.fastsearch.api.strategies;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

/**
 * @param <T> Generic type for the search results.
 * @param <SR> The type of the search request, which can be a custom request type.
 *            This allows for flexibility in defining different search parameters.
 *            For example, it can be a SingleValueSearchRequest, MultiMatchSearchRequest,
 *            or any other custom request type that extends a base search request.
 */
public interface SearchStrategy<T, SR> {
    //todo implement this method
    //    T search(ElasticsearchOperations operations, Class<T> entityClass, SearchRequest request);

    /**
     * @param operations Elasticsearch operations to be used for the search.
     * @param entityClass The class of the entity to be searched.
     * @param request The search request containing the parameters for the search.
     * @return A list of search results of type T.
     */
    List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request);

    /**
     * @param query Elasticsearch query to be executed.
     * @param operations Elasticsearch operations to be used for the search.
     * @param entityClass The class of the entity to be searched.
     * @return A list of search results of type T extracted from the SearchHits.
     * This method executes the provided query using the given Elasticsearch operations
     * and retrieves the search hits, mapping them to the specified entity class.
     * It is a default method to avoid code duplication across different search strategies.
     */
    default List<T> getHits(Query query, ElasticsearchOperations operations, Class<T> entityClass) {
        SearchHits<T> searchHits = operations.search(query, entityClass);
        return searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
