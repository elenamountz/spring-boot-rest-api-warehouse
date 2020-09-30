package app.repository;

import app.model.WareTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WareTransactionDetailRepository extends JpaRepository<WareTransactionDetail, Long> {

    List<WareTransactionDetail> findByWareTransactionId(Long wareTransactionId);
}
