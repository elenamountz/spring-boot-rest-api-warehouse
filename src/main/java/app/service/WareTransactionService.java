package app.service;

import app.dto.WareTransactionDetailDto;
import app.dto.WareTransactionDto;
import app.model.WareTransactionDetail;

import java.util.List;

public interface WareTransactionService {

    WareTransactionDto findById(Long id);

    List<WareTransactionDto> findAll();

    WareTransactionDto save(WareTransactionDto wareTransactionDto);

    void deleteById(Long id);

}
