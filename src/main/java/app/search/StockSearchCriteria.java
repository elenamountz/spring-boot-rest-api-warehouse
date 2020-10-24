package app.search;

import app.common.search.BaseSearchCriteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockSearchCriteria extends BaseSearchCriteria {

    public static final String ORDER_BY_WAREHOUSE_ID = "warehouseId";
    public static final String ORDER_BY_WAREHOUSE_DESCRIPTION = "warehouseDescription";
    public static final String ORDER_BY_SHELF_ID = "shelfId";
    public static final String ORDER_BY_SHELF_CODE = "shelfCode";
    public static final String ORDER_BY_PRODUCT_ID = "productId";
    public static final String ORDER_BY_PRODUCT_CODE = "productCode";

    public static final String DEFAULT_ORDER_BY = ORDER_BY_WAREHOUSE_ID;

    // warehouse
    private Long warehouseId;
    private String warehouseDescription;

    // shelf
    private Long shelfId;
    private String shelfCode;

    // product
    private Long productId;
    private String productCode;
}
