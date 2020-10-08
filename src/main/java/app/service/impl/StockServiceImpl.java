package app.service.impl;

import app.common.utils.DateUtils;
import app.projection.StockProjection;
import app.repository.WareTransactionDetailRepository;
import app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * Including the given date.
     */
    @Override
    public List<StockProjection> findStockByProductAndDate(String productCode, Date date) {
        return this.wareTransactionDetailRepository
                .findStockByProductAndDate(productCode, DateUtils.plusDays(date, 1));
    }
}
