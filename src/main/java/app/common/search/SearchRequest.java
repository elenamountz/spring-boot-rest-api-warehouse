package app.common.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class that represents the incoming search request from outside.
 * Contains the needed info for a paginated search.
 * Also includes a stringified JSON with search criteria (key, value pairs).
 * This JSON will feed a class that extends BaseSearchCriteria,
 * with useful domain related info.
 */
@Getter
@Setter
@ToString
public class SearchRequest {

    /**
     * Page number
     */
    private int pageIndex;

    /**
     * Number of rows per page
     */
    private int pageSize;

    /**
     * Ordering field
     */
    private String orderBy;

    /**
     * Ordering direction
     */
    private Boolean orderAsc;

    /**
     * Pagination flag
     */
    private Boolean paginated;

    /**
     * Stringified JSON that contains the search criteria
     */
    private String searchCriteriaString;
}
