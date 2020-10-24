package app.service.impl;

import app.common.search.PageSearchResult;
import app.dto.StockDto;
import app.model.Stock;
import app.projection.StockProjection;
import app.repository.StockRepository;
import app.search.StockSearchCriteria;
import app.service.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Returns a stock snapshot of a product, until the given date.
     * Accurate retrieval, using hours, minutes and seconds as parameter.
     */
    @Override
    public List<StockProjection> findStockByProductAndDate(String productCode, LocalDateTime date) {
        return this.stockRepository
                .findStockByProductAndDate(productCode, date);
    }

    @Override
    public List<StockDto> findAll() {
        return this.stockRepository.findAll()
                .stream()
                .map(StockDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public PageSearchResult<StockDto> search(StockSearchCriteria criteria) {
        PageSearchResult<Stock> page = this.stockRepository.search(criteria);
        List<StockDto> dtos = page
                .getPageData()
                .stream()
                .map(StockDto::new)
                .collect(Collectors.toList());

        return new PageSearchResult<>(page.getTotalRows(), dtos);
    }

    private Stock dtoToEntity(StockDto stockDto) {
        Stock stock = new Stock();
        BeanUtils.copyProperties(stockDto, stock);
        return stock;
    }
}
