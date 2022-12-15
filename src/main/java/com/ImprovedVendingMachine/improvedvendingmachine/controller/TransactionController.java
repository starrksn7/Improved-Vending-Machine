package com.ImprovedVendingMachine.improvedvendingmachine.controller;

import com.ImprovedVendingMachine.improvedvendingmachine.dao.ItemDao;
import com.ImprovedVendingMachine.improvedvendingmachine.dao.TransactionDao;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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
    public String depositMoney(BigDecimal moneyInserted){
        return transactionDao.depositMoney(moneyInserted);
    }

    @PostMapping(path = "/sell")
    public String makeSale(BigDecimal itemCost){
       return transactionDao.makeSale(itemCost);
    }

    @PostMapping(path = "/makeChange")
    public String makeChange(){
        return transactionDao.makeChange();
    }
}