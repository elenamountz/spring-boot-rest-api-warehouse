package app.controller;

import app.dto.ShelfDto;
import app.dto.WarehouseDto;
import app.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("{id}")
    public WarehouseDto findById(@PathVariable Long id) {
        return this.warehouseService.findById(id);
    }

    @GetMapping
    public List<WarehouseDto> findAll() {
        return this.warehouseService.findAll();
    }

    @PostMapping
    public WarehouseDto save(@RequestBody WarehouseDto warehouseDto) {
        warehouseDto.setId(null);
        return this.warehouseService.save(warehouseDto);
    }

    @PutMapping
    public WarehouseDto update(@RequestBody WarehouseDto warehouseDto) {
        return this.warehouseService.save(warehouseDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.warehouseService.deleteById(id);
    }

}
