package app.common.utils;

import app.common.search.BaseSearchCriteria;
import app.common.search.PageSearchResult;
import app.common.search.SearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        T searchCriteria = JsonUtils.parse(searchCriteriaString, searchCriteriaClass);

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

    public static OrderSpecifier<?> orderSpecifierOf(BaseSearchCriteria criteria, HashMap<String, Path> orderMap, String defaultOrderBy) {
        // Specify order direction
        Order orderDirection = (criteria.getOrderAsc() == null) || (criteria.getOrderAsc().equals(Boolean.TRUE))
                ? Order.ASC
                : Order.DESC;

        // Specify order field name
        if(StringUtils.isBlank(criteria.getOrderBy()) && criteria.getOrderBy() == null) {
            criteria.setOrderBy(defaultOrderBy);
        }

        // Specify order field path
        Path orderPath = null;
        for(Map.Entry entry : orderMap.entrySet()) {
            if(entry.getKey().equals(criteria.getOrderBy())) {
                orderPath = (Path) entry.getValue();
            }
        }

        return new OrderSpecifier(orderDirection, orderPath);
    }

}
