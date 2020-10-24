package app.repository.impl;

import app.common.search.PageSearchResult;
import app.common.search.QueryExecutor;
import app.common.utils.SearchUtils;
import app.model.*;
import app.repository.StockRepositoryCustom;
import app.search.StockSearchCriteria;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;

public class StockRepositoryImpl extends QueryExecutor implements StockRepositoryCustom {

    private static final QStock qStock = QStock.stock;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageSearchResult<Stock> search(StockSearchCriteria criteria) {
        JPAQuery<Stock> query = new JPAQuery<Stock>(entityManager)
                .from(qStock)
                .where(predicateOf(criteria))
                .orderBy(SearchUtils.orderSpecifierOf(criteria, orderMap(), StockSearchCriteria.DEFAULT_ORDER_BY));

        return super.executeQuery(criteria, query);
    }

    private Predicate predicateOf(StockSearchCriteria criteria) {
        BooleanBuilder predicate = new BooleanBuilder();

        Long productId = criteria.getProductId();
        if(productId != null) {
            predicate.and(qStock.productId.eq(criteria.getProductId()));
        }

        String productCode = criteria.getProductCode();
        if(StringUtils.isNotBlank(productCode)) {
            predicate.and(qStock.productCode.containsIgnoreCase(productCode));
        }

        Long shelfId = criteria.getShelfId();
        if(shelfId != null) {
            predicate.and(qStock.shelfId.eq(shelfId));
        }

        String shelfCode = criteria.getShelfCode();
        if(StringUtils.isNotBlank(shelfCode)) {
            predicate.and(qStock.shelfCode.containsIgnoreCase(shelfCode));
        }

        Long warehouseId = criteria.getWarehouseId();
        if(warehouseId != null) {
            predicate.and(qStock.warehouseId.eq(warehouseId));
        }

        String warehouseDescription = criteria.getWarehouseDescription();
        if(StringUtils.isNoneBlank(warehouseDescription)) {
            predicate.and(qStock.warehouseDescription.containsIgnoreCase(warehouseDescription));
        }

        return predicate;
    }

    private HashMap<String, Path> orderMap() {
        HashMap<String, Path> map = new HashMap<>();

        map.put(StockSearchCriteria.ORDER_BY_PRODUCT_ID, qStock.productId);
        map.put(StockSearchCriteria.ORDER_BY_PRODUCT_CODE, qStock.productCode);
        map.put(StockSearchCriteria.ORDER_BY_SHELF_ID, qStock.shelfId);
        map.put(StockSearchCriteria.ORDER_BY_SHELF_CODE, qStock.shelfCode);
        map.put(StockSearchCriteria.ORDER_BY_WAREHOUSE_ID, qStock.warehouseId);
        map.put(StockSearchCriteria.ORDER_BY_WAREHOUSE_DESCRIPTION, qStock.warehouseDescription);

        return map;
    }
}
