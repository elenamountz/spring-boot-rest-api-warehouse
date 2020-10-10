package app.service.impl;

import app.projection.StockProjection;
import app.repository.WareTransactionDetailRepository;
import app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final WareTransactionDetailRepository wareTransactionDetailRepository;

    @Autowired
    public StockServiceImpl(WareTransactionDetailRepository wareTransactionDetailRepository) {
        this.wareTransactionDetailRepository = wareTransactionDetailRepository;
    }

    /**
     * Returns a stock snapshot of a product, until the given date.
     * Accurate retrieval, using hours, minutes and seconds as parameter.
     */
    @Override
    public List<StockProjection> findStockByProductAndDate(String productCode, LocalDateTime date) {
        return this.wareTransactionDetailRepository
                .findStockByProductAndDate(productCode, date);
    }
}
