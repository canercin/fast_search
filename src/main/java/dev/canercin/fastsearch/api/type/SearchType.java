package dev.canercin.fastsearch.api.type;

/**
 * Enum representing the different types of search operations
 * that can be performed in the FastSearch API.
 * Each enum constant corresponds to a specific search strategy or operation type.
 */
public enum SearchType {
    BOOL_QUERY,
    DATE_RANGE,
    MATCH_ALL,
    MATCH,
    MULTI_MATCH,
    NUMERIC_RANGE,
    TERM,
    TERMS,
}
