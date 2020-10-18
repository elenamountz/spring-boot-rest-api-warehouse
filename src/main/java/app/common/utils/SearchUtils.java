package app.common.utils;

import app.common.search.BaseSearchCriteria;
import app.common.search.PageSearchResult;
import app.common.search.SearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.util.List;

public class SearchUtils {

    private static final Logger LOG = LoggerFactory.getLogger(SearchUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * @param searchRequest
     *      request object from outside
     * @param searchCriteriaClass
     *      criteria based on our domain
     * @param <T>
     *      any class extends BaseSearchCriteria
     * @return
     *      a <T> class instance that contains pagination info
     *      along with extra domain related properties as filters
     */
    public static <T extends BaseSearchCriteria> T createSearchCriteria(SearchRequest searchRequest, Class<T> searchCriteriaClass) {
        String searchCriteriaString = StringUtils.isNotBlank(searchRequest.getSearchCriteriaString())
                ? searchRequest.getSearchCriteriaString()
                : "{}";

        // Create search criteria object based on given stringified JSON
        T searchCriteria = parseJson(searchCriteriaString, searchCriteriaClass);

        // Zero indexed page
        searchCriteria.setPageIndex(searchRequest.getPageIndex() - 1);
        searchCriteria.setPageSize(searchRequest.getPageSize());
        searchCriteria.setOrderBy(searchRequest.getOrderBy());
        searchCriteria.setOrderAsc(searchRequest.getOrderAsc());
        searchCriteria.setPaginated(searchRequest.getPaginated());

        return searchCriteria;
    }

    public static Pageable pageableOf(SearchRequest searchRequest) {
        int pageIndex = searchRequest.getPageIndex();
        int rowsSize = searchRequest.getPageSize();
        String orderBy = searchRequest.getOrderBy();
        Sort.Direction orderDirection = (searchRequest.getOrderAsc() != null) && (searchRequest.getOrderAsc().equals(Boolean.TRUE))
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        return PageRequest.of(pageIndex, rowsSize, orderDirection, orderBy);
    }

    public static <T> Page<T> pageOf(SearchRequest searchRequest, PageSearchResult<T> searchResult) {
        List<T> data = searchResult.getPageData();
        Pageable pageable = pageableOf(searchRequest);
        long totalRows = searchResult.getTotalRows();

        return new PageImpl<>(data, pageable, totalRows);
    }

    private static <T> T parseJson(String jsonSearchCriteriaString, Class<T> searchCriteriaClass) {
        T searchCriteria = null;

        try {
            searchCriteria = MAPPER.readValue(jsonSearchCriteriaString, searchCriteriaClass);
        } catch (IOException e) {
            LOG.info("Something went wrong while parsing stringified JSON", e);
        }

        return searchCriteria;
    }

}
