package dev.canercin.fastsearch.api.strategies;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public interface SearchStrategy<T, SR> {
    //todo implement this method
    //    T search(ElasticsearchOperations operations, Class<T> entityClass, SearchRequest request);

    List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request);

    default List<T> getHits(Query query, ElasticsearchOperations operations, Class<T> entityClass) {
        SearchHits<T> searchHits = operations.search(query, entityClass);
        return searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
