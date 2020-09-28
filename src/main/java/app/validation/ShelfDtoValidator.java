package app.validation;

import app.common.validation.BaseValidator;
import app.common.validation.CommonValidatorUtils;
import app.dto.ShelfDto;
import app.model.Shelf;
import app.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShelfDtoValidator implements BaseValidator<ShelfDto> {

    private final ShelfRepository shelfRepository;
    private final CommonValidatorUtils<ShelfDto, Shelf> commonValidatorUtils;

    @Autowired
    public ShelfDtoValidator(ShelfRepository shelfRepository, CommonValidatorUtils<ShelfDto, Shelf> commonValidatorUtils) {
        this.shelfRepository = shelfRepository;
        this.commonValidatorUtils = commonValidatorUtils;
    }

    @Override
    public void validate(ShelfDto obj) {
        validateUniqueCode(obj);
    }

    private void validateUniqueCode(ShelfDto obj) {
        Shelf shelf = this.shelfRepository
                .findByCode(obj.getCode())
                .orElse(null);

        if(shelf != null) {
            this.commonValidatorUtils.validateUniqueField("code", obj, shelf);
        }
    }

}
