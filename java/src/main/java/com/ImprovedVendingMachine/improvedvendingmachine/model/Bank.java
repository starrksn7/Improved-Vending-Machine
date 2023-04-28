package com.ImprovedVendingMachine.improvedvendingmachine.model;

import java.math.BigDecimal;

public class Bank {

    private BigDecimal total = new BigDecimal("0.00");

    public Bank() {};

    public Bank(BigDecimal balance){
        this.total = balance;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal balance){
        this.total = balance;
    }


}
