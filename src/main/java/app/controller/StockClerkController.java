package app.controller;

import app.dto.StockClerkDto;
import app.service.StockClerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clerks")
public class StockClerkController {

    private final StockClerkService stockClerkService;

    @Autowired
    public StockClerkController(StockClerkService stockClerkService) {
        this.stockClerkService = stockClerkService;
    }

    @GetMapping("{id}")
    public StockClerkDto findById(@PathVariable Long id) {
        return this.stockClerkService.findById(id);
    }

    @GetMapping
    public List<StockClerkDto> findAll() {
        return this.stockClerkService.findAll();
    }

    @PostMapping
    public StockClerkDto save(@RequestBody StockClerkDto stockClerkDto) {
        stockClerkDto.setId(null);
        return this.stockClerkService.save(stockClerkDto);
    }

    @PutMapping
    public StockClerkDto update(@RequestBody StockClerkDto stockClerkDto) {
        return this.stockClerkService.save(stockClerkDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.stockClerkService.deleteById(id);
    }
}
