package com.ImprovedVendingMachine.improvedvendingmachine.model;

import java.math.BigDecimal;

public class Item {

    private String location;
    private String itemName;
    private BigDecimal cost;

    private String itemType;
    private int itemStock;


    public Item (){}

    public Item(String location, String itemName, BigDecimal cost, int itemStock){
        this.location = location;
        this.itemName = itemName;
        this.cost = cost;
        this.itemStock = itemStock;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getItemType(){
        return itemType;
    }

    public void setItemType(String itemType){
        this.itemType = itemType;
    }
    public int getItemStock(){
        return itemStock;
    }

    public void setItemStock(int stock){
        this.itemStock = stock;
    }
}
