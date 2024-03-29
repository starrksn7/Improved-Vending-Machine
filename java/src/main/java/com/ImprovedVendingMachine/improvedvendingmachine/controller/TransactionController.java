package com.ImprovedVendingMachine.improvedvendingmachine.controller;

import com.ImprovedVendingMachine.improvedvendingmachine.dao.BankDao;
import com.ImprovedVendingMachine.improvedvendingmachine.dao.ItemDao;
import com.ImprovedVendingMachine.improvedvendingmachine.dao.TransactionDao;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")

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
    public String depositMoney(@RequestBody Transaction transaction) throws JsonParseException {
            return transactionDao.depositMoney(transaction.getTransactionAmount());
        }

    @PostMapping(path = "/sell")
    public void makeSale(@RequestBody Item item){
       transactionDao.makeSale(item.getLocation());
    }

    @PostMapping(path = "/makeChange")
    public String makeChange(){
       return transactionDao.makeChange();
    }
}