package app.controller;

import app.dto.WareTransactionDto;
import app.service.WareTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ware-transactions")
public class WareTransactionController {

    private final WareTransactionService wareTransactionService;

    @Autowired
    public WareTransactionController(WareTransactionService wareTransactionService) {
        this.wareTransactionService = wareTransactionService;
    }

    @GetMapping("{id}")
    public WareTransactionDto findById(@PathVariable Long id) {
        return this.wareTransactionService.findById(id);
    }

    @GetMapping
    public List<WareTransactionDto> findAll() {
        return this.wareTransactionService.findAll();
    }

    @PostMapping
    public WareTransactionDto save(@RequestBody WareTransactionDto wareTransactionDto) {
        wareTransactionDto.setId(null);
        return this.wareTransactionService.save(wareTransactionDto);
    }

    @PutMapping
    public WareTransactionDto update(@RequestBody WareTransactionDto wareTransactionDto) {
        return this.wareTransactionService.save(wareTransactionDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.wareTransactionService.deleteById(id);
    }
}
