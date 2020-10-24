package app.service;

import app.dto.StockDto;
import app.projection.StockProjection;

import java.time.LocalDateTime;
import java.util.List;

public interface StockService {

    List<StockProjection> findStockByProductAndDate(String productCode, LocalDateTime date);

    List<StockDto> findAll();

}
