package app.service;

import app.projection.StockProjection;

import java.util.Date;
import java.util.List;

public interface StockService {

    List<StockProjection> findStockByProductAndDate(String productCode, Date date);
}
