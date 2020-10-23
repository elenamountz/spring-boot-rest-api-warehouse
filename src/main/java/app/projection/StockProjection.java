package app.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockProjection {

    private String warehouseDescription;
    private String shelfCode;
    private String productCode;
    private Long totalQuantity;
}
