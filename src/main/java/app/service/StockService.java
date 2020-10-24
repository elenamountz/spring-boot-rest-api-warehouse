package app.service;

import app.common.search.PageSearchResult;
import app.dto.StockDto;
import app.projection.StockProjection;
import app.search.StockSearchCriteria;

import java.time.LocalDateTime;
import java.util.List;

public interface StockService {

    List<StockProjection> findStockByProductAndDate(String productCode, LocalDateTime date);

    List<StockDto> findAll();

    PageSearchResult<StockDto> search(StockSearchCriteria criteria);

}
