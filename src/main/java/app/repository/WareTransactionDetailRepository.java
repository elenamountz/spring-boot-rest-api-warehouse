package app.repository;

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
            "WHERE detail.product.id=:productId " +
            "AND detail.shelf.id=:shelfId")
    Optional<Integer> findTotalQuantityByProductAndShelf(@Param("productId") Long productId, @Param("shelfId") Long shelfId);
}
