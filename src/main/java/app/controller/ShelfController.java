package app.controller;

import app.dto.ShelfDto;
import app.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shelves")
public class ShelfController {

    private final ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @GetMapping("{id}")
    public ShelfDto findById(@PathVariable Long id) {
        return this.shelfService.findById(id);
    }

    @GetMapping
    public List<ShelfDto> findAll() {
        return this.shelfService.findAll();
    }

    @PostMapping
    public ShelfDto save(@RequestBody ShelfDto shelfDto) {
        shelfDto.setId(null);
        return this.shelfService.save(shelfDto);
    }

    @PutMapping
    public ShelfDto update(@RequestBody ShelfDto shelfDto) {
        return this.shelfService.save(shelfDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.shelfService.deleteById(id);
    }
}
