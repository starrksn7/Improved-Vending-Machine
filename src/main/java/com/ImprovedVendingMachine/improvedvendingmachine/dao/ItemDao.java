package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;

import java.util.List;

public interface ItemDao {

    List<Item> listAllItems();

    int checkStock(String itemLocation);
}
