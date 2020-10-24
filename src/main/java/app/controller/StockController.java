package app.controller;

import app.dto.StockDto;
import app.projection.StockProjection;
import app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("snapshot")
    public List<StockProjection> findStockByProductAndDateTime(@RequestParam String productCode, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        return this.stockService.findStockByProductAndDate(productCode, date);
    }

    @GetMapping()
    public List<StockDto> findAll() {
        return this.stockService.findAll();
    }
}
