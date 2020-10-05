package app.controller;

import app.projection.StockProjection;
import app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<StockProjection> findStockByProductAndDate(@RequestParam String productCode, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return this.stockService.findStockByProductAndDate(productCode, date);
    }
}
