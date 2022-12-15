package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    List<Transaction> listAllTransactions();

    String depositMoney(BigDecimal moneyInserted);

    String makeSale(BigDecimal itemCost);

    String makeChange();
}
