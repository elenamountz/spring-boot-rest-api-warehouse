package app.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockProjection {

    private long totalQuantity;
    private String shelfCode;
    private String warehouseDescription;
}
