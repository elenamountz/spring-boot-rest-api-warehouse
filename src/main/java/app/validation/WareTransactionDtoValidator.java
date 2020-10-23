package app.validation;

import app.common.validation.BaseValidator;
import app.common.validation.CommonValidatorUtils;
import app.dto.WareTransactionDetailDto;
import app.dto.WareTransactionDto;
import app.enums.WareTransactionType;
import app.exception.InvalidQuantityException;
import app.exception.NotEnoughQuantityToExportException;
import app.repository.WareTransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WareTransactionDtoValidator implements BaseValidator<WareTransactionDto> {

    private static final String FIELD_WARE_TRANSACTION_TYPE = "Ware transaction type";
    private static final String FIELD_STOCK_CLERK_ID = "Stock clerk";
    private static final String FIELD_WARE_TRANSACTION_DATE = "Ware transaction date";

    private final CommonValidatorUtils commonValidatorUtils;
    private final WareTransactionDetailDtoValidator wareTransactionDetailDtoValidator;
    private final WareTransactionDetailRepository wareTransactionDetailRepository;

    @Autowired
    public WareTransactionDtoValidator(
            CommonValidatorUtils commonValidatorUtils,
            WareTransactionDetailDtoValidator wareTransactionDetailDtoValidator,
            WareTransactionDetailRepository wareTransactionDetailRepository) {
        this.commonValidatorUtils = commonValidatorUtils;
        this.wareTransactionDetailDtoValidator = wareTransactionDetailDtoValidator;
        this.wareTransactionDetailRepository = wareTransactionDetailRepository;
    }

    @Override
    public void validate(WareTransactionDto obj) {
        this.commonValidatorUtils.validateRequiredField(FIELD_WARE_TRANSACTION_TYPE, obj.getWareTransactionType());
        this.commonValidatorUtils.validateRequiredField(FIELD_STOCK_CLERK_ID, obj.getStockClerkId());
        this.commonValidatorUtils.validateRequiredField(FIELD_WARE_TRANSACTION_DATE, obj.getTransactionDate());
        validateWareTransactionDetailDtos(obj.getWareTransactionDetails());
        validateWareTransactionDetailQuantity(obj.getWareTransactionDetails());

        if(obj.getWareTransactionType().equals(WareTransactionType.EXPORT)) {
            this.validateExportQuantity(obj.getWareTransactionDetails());
        }
    }

    private void validateWareTransactionDetailDtos(List<WareTransactionDetailDto> wTxDetails) {
        for(WareTransactionDetailDto wTxDetail : wTxDetails) {
            this.wareTransactionDetailDtoValidator.validate(wTxDetail);
        }
    }

    private void validateWareTransactionDetailQuantity(List<WareTransactionDetailDto> wTxDetails) {
        for(WareTransactionDetailDto wTxDetail : wTxDetails) {
            if(!(wTxDetail.getQuantity() > 0)) {
                Long productId = wTxDetail.getProductId();
                Long shelfId = wTxDetail.getShelfId();
                throw new InvalidQuantityException("product " + productId + " in shelf " + shelfId);
            }
        }
    }

    /**
     * Validation that checks if the requested quantity of a product can be exported.
     * Consider the transaction successful only if the stored quantity is equal
     * or greater than the requested one. Otherwise, throw a custom exception.
     */
    private void validateExportQuantity(List<WareTransactionDetailDto> wTxDetails) {
        for(WareTransactionDetailDto wTxDetail : wTxDetails) {
            Long productId = wTxDetail.getProductId();
            Long shelfId = wTxDetail.getShelfId();

            Long totalImports = this.wareTransactionDetailRepository
                    .findTotalQuantityByProductAndShelfAndWareTransactionType(productId, shelfId, WareTransactionType.IMPORT)
                    .orElse((long) 0);
            Long totalExports = this.wareTransactionDetailRepository
                    .findTotalQuantityByProductAndShelfAndWareTransactionType(productId, shelfId, WareTransactionType.EXPORT)
                    .orElse((long) 0);

            Long totalExistingQuantity = totalImports - totalExports;
            Long requestedQuantity = wTxDetail.getQuantity();

            if (requestedQuantity > totalExistingQuantity) {
                throw new NotEnoughQuantityToExportException("product " + productId + " in shelf " + shelfId);
            }

        }
    }

}
