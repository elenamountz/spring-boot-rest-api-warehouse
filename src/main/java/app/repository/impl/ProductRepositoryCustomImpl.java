package app.repository.impl;

import app.common.search.QueryExecutor;
import app.common.search.PageSearchResult;
import app.enums.MeasurementUnit;
import app.model.Product;
import app.model.QProduct;
import app.repository.ProductRepositoryCustom;
import app.search.ProductSearchCriteria;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

public class ProductRepositoryCustomImpl extends QueryExecutor implements ProductRepositoryCustom {

    private static final QProduct qProduct = QProduct.product;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageSearchResult<Product> search(ProductSearchCriteria criteria) {
        JPAQuery<Product> query = new JPAQuery<Product>(entityManager)
                .from(qProduct)
                .where(predicateOf(criteria))
                .orderBy(orderSpecifierOf(criteria, orderMap(), ProductSearchCriteria.DEFAULT_ORDER_BY));

        return super.executeQuery(criteria, query);
    }

    private Predicate predicateOf(ProductSearchCriteria criteria) {
        BooleanBuilder predicate = new BooleanBuilder();

        Long id = criteria.getId();
        if(id != null)
            predicate.and(qProduct.id.eq(criteria.getId()));

        String code = criteria.getCode();
        if(StringUtils.isNotBlank(code))
            predicate.and(qProduct.code.containsIgnoreCase(code));

        MeasurementUnit measurementUnit = criteria.getMeasurementUnit();
        if(measurementUnit != null)
            predicate.and(qProduct.measurementUnit.eq(measurementUnit));

        String description = criteria.getDescription();
        if(StringUtils.isNotBlank(description))
            predicate.and(qProduct.description.containsIgnoreCase(description));

        return predicate;
    }

    private HashMap<String, Path> orderMap() {
        HashMap<String, Path> map = new HashMap<>();

        map.put(ProductSearchCriteria.ORDER_BY_ID, qProduct.id);
        map.put(ProductSearchCriteria.ORDER_BY_CODE, qProduct.code);
        map.put(ProductSearchCriteria.ORDER_BY_MEASUREMENT_UNIT, qProduct.measurementUnit);

        return map;
    }

    private OrderSpecifier<?> orderSpecifierOf(ProductSearchCriteria criteria, HashMap<String, Path> orderMap, String defaultOrderBy) {

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
