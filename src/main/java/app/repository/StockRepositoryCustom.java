package app.repository;

import app.common.search.PageSearchResult;
import app.model.Stock;
import app.search.StockSearchCriteria;


public interface StockRepositoryCustom {

    PageSearchResult<Stock> search(StockSearchCriteria criteria);
}