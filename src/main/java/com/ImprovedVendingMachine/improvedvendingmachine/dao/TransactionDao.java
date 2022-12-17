package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;

import java.util.List;

public interface TransactionDao {

    List<Transaction> listAllTransactions();

    String depositMoney(Transaction transaction);

    String makeSale(Transaction transaction, Item item);

    void makeChange(Transaction transaction);
}
