package app.repository;

import app.enums.WareTransactionType;
import app.model.WareTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    Optional<Long> findTotalQuantityByProductAndShelfAndWareTransactionType(
            @Param("productId") Long productId,
            @Param("shelfId") Long shelfId,
            @Param("wareTransactionType") WareTransactionType wareTransactionType);

}
