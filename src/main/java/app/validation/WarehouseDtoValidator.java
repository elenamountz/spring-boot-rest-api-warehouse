package app.validation;

import app.common.validation.BaseValidator;
import app.dto.ShelfDto;
import app.dto.WarehouseDto;
import app.exception.FieldNotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class WarehouseDtoValidator implements BaseValidator<WarehouseDto> {

    private final ShelfDtoValidator shelfDtoValidator;

    @Autowired
    public WarehouseDtoValidator(ShelfDtoValidator shelfDtoValidator) {
        this.shelfDtoValidator = shelfDtoValidator;
    }

    @Override
    public void validate(WarehouseDto obj) {
        validateUniqueShelfCodes(obj.getShelves());
    }

    private void validateUniqueShelfCodes(List<ShelfDto> shelfDtos) {
        Set<String> uniqueCodes = new HashSet<String>();
        for (ShelfDto shelfDto : shelfDtos) {
            String code = shelfDto.getCode();

            // Check if list contains two or more identical codes
            if (uniqueCodes.contains(code))
                throw new FieldNotUniqueException(code);
            else
                // Check if code is already committed
                shelfDtoValidator.validate(shelfDto);
                uniqueCodes.add(code);
        }
    }

}