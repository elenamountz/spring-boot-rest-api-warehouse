package app.repository;

import app.common.search.PageSearchResult;
import app.model.Product;
import app.search.ProductSearchCriteria;

public interface ProductRepositoryCustom {

    PageSearchResult<Product> search(ProductSearchCriteria criteria);
}
