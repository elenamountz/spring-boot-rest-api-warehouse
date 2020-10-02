package app.service;

import app.dto.WarehouseDto;

import java.util.List;

public interface WarehouseService {

    WarehouseDto findById(Long id);

    List<WarehouseDto> findAll();

    WarehouseDto save(WarehouseDto warehouseDto);

    void deleteById(Long id);
}
