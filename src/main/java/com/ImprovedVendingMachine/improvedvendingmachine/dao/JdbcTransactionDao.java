package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Bank;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;
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
    private BankDao bankDao;

    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm:ss"));


    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transaction> listAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT transaction_id, transaction_date_time, action_taken, transaction_amount FROM transactions;";

        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Transaction transaction = this.mapRowToTransaction(results);
            transactions.add(transaction);
        }
        return transactions;
    }

    public String depositMoney(BigDecimal transactionAmount) {

        Bank balance = new Bank();
        String insertSql = "INSERT INTO transactions (transaction_date_time, action_taken, transaction_amount) " +
                "VALUES (?, 'deposit', ?); ";
        jdbcTemplate.update(insertSql, now, transactionAmount);

        String updateSql = "UPDATE balance SET balance_total = balance_total + ?;";
        jdbcTemplate.update(updateSql, transactionAmount);

        String returnBalanceSql = "SELECT balance_total FROM balance;";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(returnBalanceSql);

        if (results.next()){
            balance = mapToBank(results);
        }

        return String.format("Your balance is %s", balance.getBalance());

    }

    public String makeSale(BigDecimal cost, String location) {

        Bank balance = new Bank();

        String checkStockSql = "SELECT location_code, item_name, item_cost, item_type, item_stock FROM items WHERE location_code = '?';";
        SqlRowSet itemSearchResults = this.jdbcTemplate.queryForRowSet(checkStockSql);
        Item item = new Item();
        if (itemSearchResults.next()) {
            item = mapRowToItem(itemSearchResults);
        }

        String returnBalanceSql = "SELECT balance_total FROM balance;";
        SqlRowSet balanceResults = this.jdbcTemplate.queryForRowSet(returnBalanceSql);

        if (balanceResults.next()){
            balance = mapToBank(balanceResults);
        }

        if (item.getItemStock() > 0) {
            if (balance.getBalance().compareTo(item.getCost()) == 0 || balance.getBalance().compareTo(item.getCost()) == 1) {
                String transactionSql = "INSERT INTO transactions (transaction_date_time, action_taken, transaction_amount) " +
                        "VALUES (?, 'sale', ?); ";
                jdbcTemplate.update(transactionSql, now, cost);

                String updateStockSql = "UPDATE items SET item_stock = item_stock - 1 WHERE location_code = ?; ";
                jdbcTemplate.update(updateStockSql, location);

                String updateBalanceSql = "UPDATE balance SET balance_total = balance_total - ?;";
                jdbcTemplate.update(updateBalanceSql, cost);

                String postSaleBalanceSql = "SELECT balance_total FROM balance;";
                SqlRowSet updateBalanceAfterSaleRowSet = this.jdbcTemplate.queryForRowSet(postSaleBalanceSql);

                if (updateBalanceAfterSaleRowSet.next()){
                    balance = mapToBank(updateBalanceAfterSaleRowSet);
                }

                if (item.getLocation().contains("A")) {
                    return String.format("Crunch, Crunch Yum! Your balance is %s", balance.getBalance());
                } else if (item.getLocation().contains("B")) {
                    return String.format("Munch, Munch, Yum! Your balance is %s", balance.getBalance());
                } else if (item.getLocation().contains("C")) {
                    return String.format("Glug, Glug Yum! Your balance is %s", balance.getBalance());
                } else {
                    return String.format("Chew, Chew Yum! Your balance is %s", balance.getBalance());
                }
            }
        }
            return "That item is currently out of stock";

    }

    public void makeChange(Transaction transaction) {


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
        return transaction;
    }

    private Item mapRowToItem(SqlRowSet rowSet) {
        Item item = new Item();
        item.setItemName(rowSet.getString("item_name"));
        item.setLocation(rowSet.getString("location_code"));
        item.setCost(rowSet.getBigDecimal("item_cost"));
        item.setItemType(rowSet.getString("item_type"));
        item.setItemStock(rowSet.getInt("item_stock"));
        return item;
    }

    private Bank mapToBank(SqlRowSet rowSet){
        Bank balance = new Bank();

        balance.setBalance(rowSet.getBigDecimal("balance_total"));

        return balance;
    }
}
