package app.validation;

import app.common.validation.CommonValidatorUtils;
import app.common.validation.BaseValidator;
import app.dto.ProductDto;
import app.model.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoValidator implements BaseValidator<ProductDto> {

    private static final String FIELD_CODE = "code";

    private final ProductRepository productRepository;
    private final CommonValidatorUtils<ProductDto, Product> commonValidatorUtils;

    @Autowired
    public ProductDtoValidator(
            ProductRepository productRepository,
            CommonValidatorUtils commonValidatorUtils) {
        this.productRepository = productRepository;
        this.commonValidatorUtils = commonValidatorUtils;
    }

    @Override
    public void validate(ProductDto obj) {
        validateUniqueCode(obj);
    }

    private void validateUniqueCode(ProductDto productDto) {

        Product product = this.productRepository
                .findByCode(productDto.getCode())
                .orElse(null);

        // Found product with the given code
        if (product != null) {
            commonValidatorUtils.validateUniqueField(FIELD_CODE, productDto, product);
        }

    }
}
