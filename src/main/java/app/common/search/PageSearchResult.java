package app.common.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageSearchResult<T> {

    /**
     * Number of rows, before pagination
     */
    private long totalRows;

    /**
     * Rows found, after pagination
     */
    private List<T> pageData;
}
