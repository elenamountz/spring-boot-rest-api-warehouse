package app.common.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Base class that contains the minimum required parameters for a paginated search.
 */
@Getter
@Setter
@ToString
public class BaseSearchCriteria {

    private int pageIndex;
    private int pageSize;
    private String orderBy;
    private Boolean orderAsc;
    private Boolean paginated;
}
