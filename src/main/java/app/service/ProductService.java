package app.service;

import app.common.search.PageSearchResult;
import app.dto.ProductDto;
import app.search.ProductSearchCriteria;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    ProductDto save(ProductDto productDto);

    void deleteById(Long id);

    PageSearchResult<ProductDto> search(ProductSearchCriteria criteria);
}
