package com.ImprovedVendingMachine.improvedvendingmachine.controller;

import com.ImprovedVendingMachine.improvedvendingmachine.dao.TransactionDao;
import com.ImprovedVendingMachine.improvedvendingmachine.dao.ItemDao;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private ItemDao itemDao;
    private TransactionDao transactionDao;

    public ItemController(ItemDao itemDao, TransactionDao transactionDao){
        this.itemDao = itemDao;
        this.transactionDao = transactionDao;
    }

    @GetMapping(path = "")
    public List<Item> listAllItems(){
        return itemDao.listAllItems();
    }

    @GetMapping(path = "/{location}")
    public int checkStock(@PathVariable ("location") String itemLocation){
        return itemDao.checkStock(itemLocation);
    }
}