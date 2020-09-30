package app.model;

import app.enums.WareTransactionType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Master entity for massive ware import/export
 */
@Data
@Entity
@Table(name = "ware_transaction")
public class WareTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ware_transaction_type")
    private WareTransactionType wareTransactionType;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "transaction_date")
    private Date transactionDate;

    /**
     * Employee that received/delivered the ware
     */
    @OneToOne
    @JoinColumn(name = "stock_clerk_id")
    private StockClerk stockClerk;

    @OneToMany(mappedBy = "wareTransaction",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    List<WareTransactionDetail> wareTransactionDetails;

}
