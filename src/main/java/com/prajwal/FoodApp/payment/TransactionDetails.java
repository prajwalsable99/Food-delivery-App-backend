package com.prajwal.FoodApp.payment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDetails {

    private String payOrderId;
    private String payCurrency;
    private Long payAmount;
    private String payKey;

    public TransactionDetails() {
    }

    public TransactionDetails(String payOrderId, String payCurrency, Long payAmount,String payKey) {
        this.payOrderId = payOrderId;
        this.payCurrency = payCurrency;
        this.payAmount = payAmount;
        this.payKey=payKey;
    }

}
