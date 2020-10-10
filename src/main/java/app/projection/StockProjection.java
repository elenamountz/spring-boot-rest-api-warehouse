package app.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockProjection {

    private String warehouseDescription;
    private String shelfCode;
    private long totalQuantity;
}
