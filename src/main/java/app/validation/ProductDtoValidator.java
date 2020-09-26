package app.validation;

import app.exception.DuplicateCodeException;
import app.common.validation.BaseValidator;
import app.dto.ProductDto;
import app.model.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoValidator implements BaseValidator<ProductDto> {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDtoValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void validate(ProductDto obj) {
        validateUniqueCode(obj.getCode());
    }

    private void validateUniqueCode(String code) {
        Product product = this.productRepository
                .findByCode(code)
                .orElse(null);

        if (product != null) {
            throw new DuplicateCodeException(code);
        }
    }
}
