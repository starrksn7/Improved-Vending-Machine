package com.ImprovedVendingMachine.improvedvendingmachine.controller;

import com.ImprovedVendingMachine.improvedvendingmachine.dao.ItemDao;
import com.ImprovedVendingMachine.improvedvendingmachine.dao.TransactionDao;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private ItemDao itemDao;
    private TransactionDao transactionDao;

    public TransactionController(ItemDao itemDao, TransactionDao transactionDao){
        this.itemDao = itemDao;
        this.transactionDao = transactionDao;
    }

    @GetMapping(path = "/all")
    public List<Transaction> listAllTransactions(){
        return transactionDao.listAllTransactions();
    }

    @PostMapping(path = "/deposit")
    public String depositMoney(Transaction transaction){
        return transactionDao.depositMoney(transaction);
    }

    @PostMapping(path = "/sell")
    public String makeSale(Transaction transaction, Item item){
       return transactionDao.makeSale(transaction, item);
    }

    @PostMapping(path = "/makeChange")
    public void makeChange(Transaction transaction){
        transactionDao.makeChange(transaction);
    }
}