package app.service;

import app.dto.ShelfDto;

import java.util.List;

public interface ShelfService {

    ShelfDto findById(Long id);

    List<ShelfDto> findAll();

    ShelfDto save(ShelfDto shelfDto);

    void deleteById(Long id);
}
