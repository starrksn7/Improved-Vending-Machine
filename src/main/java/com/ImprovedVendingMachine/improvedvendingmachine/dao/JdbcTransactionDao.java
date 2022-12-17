package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {

    private final JdbcTemplate jdbcTemplate;

    private ItemDao itemDao;
    private TransactionDao transactionDao;


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

    public String depositMoney(Transaction transaction) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"));

        String sql = "INSERT INTO transactions (transaction_date_time, action_taken, transaction_amount, balance) " +
                "VALUES (?, 'deposit', ?, ?); ";

        transaction.deposit(transaction.getTransactionAmount());
        jdbcTemplate.update(sql, now, transaction.getTransactionAmount(), transaction.getBalance());

        return String.format("Your balance is now %s", transaction.getBalance());
    }

    public String makeSale(Transaction transaction, Item item) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"));

        String sql = "INSERT INTO transactions (transaction_date_time, action_taken, prior_balance, new_balance" +
                "VALUES (?, 'sale', ?, ?); ";

        transaction.sell(transaction.getTransactionAmount());

        jdbcTemplate.update(sql, now, transaction.getTransactionAmount(), transaction.getBalance());

        if(item.getLocation().contains("A")){
            return "Crunch, Crunch Yum!";
        } else if (item.getLocation().contains("B")){
            return "Munch, Munch, Yum!";
        } else if (item.getLocation().contains("C")){
            return "Glug, Glug Yum!";
        } else {
            return "Chew, Chew Yum!";
        }
    }

    public void makeChange(Transaction transaction) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"));

        String sql = "INSERT INTO transactions (transaction_date_time, action_taken, prior_balance, new_balance" +
                "VALUES (?, 'gave change', prior_balance, 0); ";

        transaction.giveChange();
        jdbcTemplate.update(sql, now);

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
