package app.dto;

import app.enums.WareTransactionType;
import app.model.StockClerk;
import app.model.WareTransaction;
import app.model.WareTransactionDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class WareTransactionDto {

    private Long id;
    private WareTransactionType wareTransactionType;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date transactionDate;

    private Long stockClerkId;
    private List<WareTransactionDetailDto> wareTransactionDetails = new ArrayList<>();

    public WareTransactionDto(WareTransaction wareTransaction) {
        BeanUtils.copyProperties(wareTransaction, this, "wareTransactionDetails");

        StockClerk stockClerk = wareTransaction.getStockClerk();
        if (stockClerk != null) {
            this.stockClerkId = stockClerk.getId();
        }

        List<WareTransactionDetail> wareTransactionDetails = wareTransaction.getWareTransactionDetails();
        if (wareTransactionDetails != null && wareTransactionDetails.size() > 0) {
            wareTransactionDetails.forEach(wTxDetails -> {
                this.wareTransactionDetails.add(new WareTransactionDetailDto(wTxDetails));
            });
        }
    }

}
