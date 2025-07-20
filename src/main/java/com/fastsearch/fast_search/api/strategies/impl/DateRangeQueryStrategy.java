package com.fastsearch.fast_search.api.strategies.impl;

import com.fastsearch.fast_search.api.requests.impl.RangeSearchRequest;
import com.fastsearch.fast_search.api.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

public class DateRangeQueryStrategy<T, SR extends RangeSearchRequest> implements SearchStrategy<T, SR> {

    /*
     * Date Range Query, belirlenen alan üzerinde verilen tarih aralığında belgeleri arar.
     * */

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        String fieldName = request.getField();
        String startDate = request.getFrom().toString();
        String endDate = request.getTo().toString();

        Query query = NativeQuery.builder()
                .withQuery(q ->
                        q.range(r ->
                                r.date(d ->
                                        d.field(fieldName)
                                                .gte(startDate)
                                                .lte(endDate)
                                )
                        )
                )
                .build();

        return getHits(query, operations, entityClass);
    }
}
