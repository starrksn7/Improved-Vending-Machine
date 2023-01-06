package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    List<Transaction> listAllTransactions();

    String depositMoney(BigDecimal transactionAmount);

    String makeSale(BigDecimal cost, String locationCode);

    void makeChange(Transaction transaction);
}
