package app.model;

import lombok.Data;


import javax.annotation.concurrent.Immutable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Stock entity represents the stock db view
 */
@Entity
@Table(name = "stock")
@Data
@Immutable
public class Stock {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "warehouse_description")
    private String warehouseDescription;

    @Column(name = "shelf_id")
    private Long shelfId;

    @Column(name = "shelf_code")
    private String shelfCode;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "total_quantity")
    private Long totalQuantity;
}
