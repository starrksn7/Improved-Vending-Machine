package com.ImprovedVendingMachine.improvedvendingmachine.controller;

import com.ImprovedVendingMachine.improvedvendingmachine.dao.BankDao;
import com.ImprovedVendingMachine.improvedvendingmachine.dao.ItemDao;
import com.ImprovedVendingMachine.improvedvendingmachine.dao.TransactionDao;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private ItemDao itemDao;
    private TransactionDao transactionDao;

    private BankDao bankDao;

    public TransactionController(ItemDao itemDao, TransactionDao transactionDao, BankDao bankDao){
        this.itemDao = itemDao;
        this.transactionDao = transactionDao;
        this.bankDao = bankDao;
    }

    @GetMapping(path = "/all")
    public List<Transaction> listAllTransactions(){
        return transactionDao.listAllTransactions();
    }

    @PostMapping(path = "/deposit")
    public String depositMoney(@RequestBody Transaction transaction){
        return transactionDao.depositMoney(transaction.getTransactionAmount());
    }

    @PostMapping(path = "/sell")
    public String makeSale(@RequestBody Item item){
       return transactionDao.makeSale(item.getLocation());
    }

    @PostMapping(path = "/makeChange")
    public String makeChange(){
       return transactionDao.makeChange();
    }
}