package com.ImprovedVendingMachine.improvedvendingmachine.model;

import java.math.BigDecimal;

public class Transaction {

    private int transaction_id;
    private String transactionDateTime;
    private String actionTaken;
    private BigDecimal transactionAmount;
    private BigDecimal balance = new BigDecimal("0.00");

    public Transaction() {};

//    public Transaction(int transaction_id, String transactionDateTime, String actionTaken, BigDecimal transactionAmount, BigDecimal balance){
//        this.transaction_id = transaction_id;
//        this.transactionDateTime = transactionDateTime;
//        this.actionTaken = actionTaken;
//        this.transactionAmount = transactionAmount;
//        this.balance = balance;
//    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String deposit(BigDecimal moneyInserted){
        balance = balance.add(moneyInserted);
        return String.format("Your balance is now %s", balance);
    }

    public String sell(BigDecimal itemCost){
        if(balance.compareTo(itemCost) == 0 || balance.compareTo(itemCost) == 1) {
            balance = balance.subtract(itemCost);
            return String.format("Your balance is now %", balance);
        } else {
            return "You lack sufficient funds for that item.  Please make a deposit";
        }
    }

    public String giveChange(){
        BigDecimal quarterValue = new BigDecimal(".25");
        BigDecimal dimeValue = new BigDecimal(".10");
        BigDecimal nickelValue = new BigDecimal(".05");
        BigDecimal pennyValue = new BigDecimal(".01");
        int numOfQuarters = 0;
        int numOfDimes = 0;
        int numOfNickels = 0;
        int numOfPennies = 0;

        while(true){
            if(balance.compareTo(quarterValue) == 0 || balance.compareTo(quarterValue) == 1){
                balance = balance.subtract(quarterValue);
                numOfQuarters++;
            } else if (balance.compareTo(dimeValue) == 0 || balance.compareTo(dimeValue) == 1){
                balance = balance.subtract(dimeValue);
                numOfDimes++;
            } else if (balance.compareTo(nickelValue) == 0 || balance.compareTo(nickelValue) == 1){
                balance = balance.subtract(nickelValue);
                numOfNickels++;
            } else if (balance.compareTo(pennyValue) == 0 || balance.compareTo(pennyValue) == 1){
                balance = balance.subtract(pennyValue);
                numOfPennies++;
            } else {
                break;
            }
        }

        return String.format("Your change is %s quarters, %s dimes, %s nickels, and %s pennies", numOfQuarters, numOfDimes, numOfNickels, numOfPennies) ;
    }
}
