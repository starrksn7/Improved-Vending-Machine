package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;
import com.fasterxml.jackson.core.JsonParseException;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    List<Transaction> listAllTransactions();

    String depositMoney(BigDecimal transactionAmount) throws JsonParseException;

    void makeSale(String locationCode);

    String makeChange();
}
