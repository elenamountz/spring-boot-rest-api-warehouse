package app.service;

import app.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    ProductDto save(ProductDto productDto);

    void deleteById(Long id);

}
