package com.ImprovedVendingMachine.improvedvendingmachine.model;

import java.math.BigDecimal;

public class Transaction {

    private int transaction_id;
    private String transactionDateTime;
    private String actionTaken;
    private BigDecimal transactionAmount;
    private BigDecimal balance;

    public Transaction() {};

    public Transaction(int transaction_id, String transactionDateTime, String actionTaken, BigDecimal transactionAmount, BigDecimal balance){
        this.transaction_id = transaction_id;
        this.transactionDateTime = transactionDateTime;
        this.actionTaken = actionTaken;
        this.transactionAmount = transactionAmount;
        this.balance = balance;
    }

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
}
