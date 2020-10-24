package app.dto;

import app.model.Stock;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class StockDto {

    private String id;
    private Long warehouseId;
    private String warehouseDescription;
    private Long shelfId;
    private String shelfCode;
    private Long productId;
    private String productCode;
    private Long totalQuantity;

    public StockDto(Stock stock) {
        BeanUtils.copyProperties(stock, this);
    }
}
