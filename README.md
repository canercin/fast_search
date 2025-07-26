# FastSearch API
FastSearch is a wrapper library written on top of Spring Data Elasticsearch to quickly and easily create Elasticsearch queries.

# How to use

## Add project dependency
If you are using Maven, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>dev.canercin</groupId>
    <artifactId>fastsearch</artifactId>
    <version>1.0.1</version>
</dependency>
```

If you are using Gradle, add the following dependency to your `build.gradle`:

```groovy
implementation group: 'dev.canercin', name: 'fastsearch', version: '1.0.1'
```

Create a factory bean to create `SearchContext` instances for your entity classes.

```java 
@Component
public class SearchContextFactory {

    public <T> SearchContext<T> create(Class<T> clazz) {
        return new SearchContext<>();
    }
}
```

# Which search types are supported?

FastSearch supports the following search types:

-   `BOOL_QUERY`: Used to combine multiple queries with logical operators (must, should, must_not, filter).
-   `DATE_RANGE`: Used to search within a specific date range.
-   `MATCH_ALL`: A query that matches all documents.
-   `MATCH`: Standard text search. It analyzes the text and finds matching documents.
-   `MULTI_MATCH`: A version of the `MATCH` query that can run on multiple fields.
-   `NUMERIC_RANGE`: Used to search within a numeric range.
-   `TERM`: Finds documents that contain the exact term specified.
-   `TERMS`: Finds documents that contain any of the multiple exact terms specified.

## Using SearchContext
You can use the `SearchContext` to build your search queries. Here is an example of how to inject it into a service:

```java
@Service
public class MySearchService {
    private final SearchContext<SampleEntity> searchContext;
    
    public MySearchService(SearchContextFactory searchContextFactory) {
        this.searchContext = searchContextFactory.create(SampleEntity.class);
    }
}
```

# Example usage

## Create Match All Query

```java
public List<SampleEntity> matchAll(){
    return searchContext.search(SearchType.MATCH_ALL, elasticsearchOperations, SampleEntity.class, null);
}
```

## Create Match Query

```java
public List<SampleEntity> match() {
    SingleValueSearchRequest singleValueSearchRequest = new SingleValueSearchRequest();
    singleValueSearchRequest.setField("name");
    singleValueSearchRequest.setValue("John Doe");
    return searchContext.search(SearchType.MATCH, elasticsearchOperations, SampleEntity.class, singleValueSearchRequest);
}
```

## Create Range Query

FastSearch supports numeric and date range query types. Type conversion is provided according to the selected search type.

```java
public List<SampleEntity> range() {
    RangeSearchRequest rangeSearchRequest = new RangeSearchRequest();
    rangeSearchRequest.setField("age");
    rangeSearchRequest.setFrom(18);
    rangeSearchRequest.setTo(30);
    return searchContext.search(SearchType.NUMERIC_RANGE, elasticsearchOperations, SampleEntity.class, rangeSearchRequest);
}
```

## Create Bool Query

```java
public List<SampleEntity> boolQuery() {
        BoolQuerySearchRequest boolQuerySearchRequest = new BoolQuerySearchRequest();

        createMustQuery(boolQuerySearchRequest);
        createShouldQuery(boolQuerySearchRequest);

        return searchContext.search(SearchType.BOOL_QUERY, elasticsearchOperations, SampleEntity.class, boolQuerySearchRequest);
    }

    private void createShouldQuery(BoolQuerySearchRequest boolQuerySearchRequest) {
        Query sampleQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .match(m -> m
                                .field("firstName")
                                .query("sample1")
                        )
                )
                .build()
                .getQuery();

        boolQuerySearchRequest.addQuery("should", List.of(sampleQuery));
    }

    private void createMustQuery(BoolQuerySearchRequest boolQuerySearchRequest) {
        Query sampleQuery = NativeQuery.builder()
                .withQuery(q -> q
                                .term(t -> t
                                        .field("email")
                                        .value("sample1@samplemail.com")
                                )
                )
                .build()
                .getQuery();

        boolQuerySearchRequest.addQuery("must", List.of(sampleQuery));
    }
```

# Example use project
The following project contains sample use cases for all available queries. 
Please take a look at the `PersonelExtendedServiceImpl` class.

[Click here for go to sample project](https://github.com/canercin/elasticSearch)