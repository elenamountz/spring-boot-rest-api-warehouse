package app.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Detail entity for massive ware import/export
 */
@Data
@Entity
@Table(name = "ware_transaction_detail")
public class WareTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ware_transaction_id")
    private WareTransaction wareTransaction;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    @Column(name = "quantity")
    private Long quantity;

}
