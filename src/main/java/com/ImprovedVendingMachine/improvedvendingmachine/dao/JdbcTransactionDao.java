package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {

    private final JdbcTemplate jdbcTemplate;

    private ItemDao itemDao;
    private TransactionDao transactionDao;

    private BigDecimal balance = new BigDecimal("0.00");

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transaction> listAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT transaction_id, transaction_date_time, action_taken, transaction_amount, balance FROM transactions;";

        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Transaction transaction = this.mapRowToTransaction(results);
            transactions.add(transaction);
        }
        return transactions;
    }

    public String depositMoney(BigDecimal moneyInserted) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"));

        String sql = "INSERT INTO transactions (transaction_date_time, action_taken, transaction_amount, balance) " +
                "VALUES (?, 'deposit', ?, ?); ";

        balance = balance.add(moneyInserted);
        jdbcTemplate.update(sql, now, moneyInserted, balance);

        return String.format("Your balance is now %s", balance);
    }

    public String makeSale(BigDecimal itemCost) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"));

        String sql = "INSERT INTO transactions (transaction_date_time, action_taken, prior_balance, new_balance" +
                "VALUES (?, 'sale', ?, ?); ";

        if(balance.compareTo(itemCost) == 0 || balance.compareTo(itemCost) == 1) {
            balance = balance.subtract(itemCost);
            jdbcTemplate.update(sql, now, itemCost, balance);
            return String.format("Your balance is now %", balance);
        } else {
            return "You lack sufficient funds for that item.  Please make a deposit";
        }
    }

    public String makeChange() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"));

        String sql = "INSERT INTO transactions (transaction_date_time, action_taken, prior_balance, new_balance" +
                "VALUES (?, 'gave change', prior_balance, 0); ";

        jdbcTemplate.update(sql, now);

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

    private Transaction mapRowToTransaction(SqlRowSet rowSet) {
        Transaction transaction = new Transaction();
        transaction.setTransaction_id(rowSet.getInt("transaction_id"));
        transaction.setTransactionDateTime(rowSet.getString("transaction_date_time"));
        transaction.setActionTaken(rowSet.getString("action_taken"));
        transaction.setTransactionAmount(rowSet.getBigDecimal("transaction_amount"));
        transaction.setBalance(rowSet.getBigDecimal("balance"));
        return transaction;
    }
}
