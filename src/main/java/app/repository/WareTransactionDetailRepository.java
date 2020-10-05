package app.repository;

import app.enums.WareTransactionType;
import app.model.WareTransactionDetail;
import app.projection.StockProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WareTransactionDetailRepository extends JpaRepository<WareTransactionDetail, Long> {

    List<WareTransactionDetail> findByWareTransactionId(Long wareTransactionId);

    @Query("SELECT SUM(detail.quantity) " +
            "FROM WareTransactionDetail detail " +
            "INNER JOIN WareTransaction tx ON detail.wareTransaction.id=tx.id " +
            "WHERE tx.wareTransactionType=:wareTransactionType " +
            "AND detail.product.id=:productId " +
            "AND detail.shelf.id=:shelfId")
    Optional<Integer> findTotalQuantityByProductAndShelfAndWareTransactionType(
            @Param("productId") Long productId,
            @Param("shelfId") Long shelfId,
            @Param("wareTransactionType") WareTransactionType wareTransactionType);


    @Query("SELECT NEW app.projection.StockProjection(" +
            "SUM(CASE WHEN tx.wareTransactionType='IMPORT' " +
            "THEN detail.quantity " +
            "ELSE -detail.quantity END), " +
            "shelf.code, " +
            "wh.description) " +
            "FROM WareTransactionDetail detail " +
            "INNER JOIN WareTransaction tx ON detail.wareTransaction.id=tx.id " +
            "INNER JOIN Product product ON detail.product.id=product.id " +
            "INNER JOIN Shelf shelf ON detail.shelf.id=shelf.id " +
            "INNER JOIN Warehouse wh ON shelf.warehouse.id=wh.id " +
            "WHERE product.code=:productCode AND tx.transactionDate<=:date " +
            "GROUP BY shelf.code, wh.description")
    List<StockProjection> findStockByProductAndDate(@Param("productCode") String productCode, @Param("date") Date date);

}
