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

    public String depositMoney(BigDecimal transactionAmount) throws IllegalArgumentException {
        BigDecimal zero = new BigDecimal("0.00");
        Bank balance = new Bank();
        String insertSql = "INSERT INTO transactions (transaction_date_time, action_taken, transaction_amount) " +
                "VALUES (?, 'deposit', ?); ";

        String updateSql = "UPDATE balance SET balance_total = balance_total + ?;";

        String returnBalanceSql = "SELECT balance_total FROM balance;";


            try {
                jdbcTemplate.update(insertSql, now, transactionAmount);
                jdbcTemplate.update(updateSql, transactionAmount);

                SqlRowSet results = this.jdbcTemplate.queryForRowSet(returnBalanceSql);
                if (results.next()){
                    balance = mapToBank(results);
                }
            } catch (IllegalArgumentException e){
                return "Please enter a valid amount to deposit.";
            }

        return String.format("Your balance is %s", balance.getBalance());

    }

    public String makeSale(String location) throws IllegalArgumentException {
        int locationCodeLength = 2;

        Item item = new Item();

        String sql = "SELECT location_code, item_name, item_cost, item_type, item_stock FROM items " +
                "WHERE location_code = ?;";

        SqlRowSet itemResults = this.jdbcTemplate.queryForRowSet(sql, location);

        if (itemResults.next()) {
            item = mapRowToItem(itemResults);
        }

        Bank balance = new Bank();

        String returnBalanceSql = "SELECT balance_total FROM balance;";
        SqlRowSet balanceResults = this.jdbcTemplate.queryForRowSet(returnBalanceSql);

        if (balanceResults.next()){
            balance = mapToBank(balanceResults);
        }

        if(listAllTransactions().contains(location)){
            try {
                if (item.getItemStock() > 0) {
                    if (balance.getBalance().compareTo(item.getCost()) == 0 || balance.getBalance().compareTo(item.getCost()) == 1) {
                        String transactionSql = "INSERT INTO transactions (transaction_date_time, action_taken, transaction_amount) " +
                                "VALUES (?, 'sale', ?); ";
                        jdbcTemplate.update(transactionSql, now, item.getCost());

                        String updateStockSql = "UPDATE items SET item_stock = item_stock - 1 WHERE location_code = ?; ";
                        jdbcTemplate.update(updateStockSql, location);

                        String updateBalanceSql = "UPDATE balance SET balance_total = balance_total - ?;";
                        jdbcTemplate.update(updateBalanceSql, item.getCost());

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
                } else {
                    return "That item is currently out of stock";
                }
            } catch (IllegalArgumentException e){
                return "Please enter a valid item location.";
            }
        }
        return "Please select a valid item.";
    }

    public String makeChange() {

        Bank balance = new Bank();

        String returnBalanceSql = "SELECT balance_total FROM balance;";
        SqlRowSet balanceResults = this.jdbcTemplate.queryForRowSet(returnBalanceSql);

        if (balanceResults.next()){
            balance = mapToBank(balanceResults);
        }

        String sql = "INSERT INTO transactions (transaction_date_time, action_taken, transaction_amount) " +
                "VALUES (?, 'gave change', ?); ";

        jdbcTemplate.update(sql, now, balance.getBalance());

        String zeroBalanceSql = "UPDATE balance SET balance_total = 0.00;";
        jdbcTemplate.update(zeroBalanceSql);

        BigDecimal quarterValue = new BigDecimal(".25");
        BigDecimal dimeValue = new BigDecimal(".10");
        BigDecimal nickelValue = new BigDecimal(".05");
        BigDecimal pennyValue = new BigDecimal(".01");
        int numOfQuarters = 0;
        int numOfDimes = 0;
        int numOfNickels = 0;
        int numOfPennies = 0;
        BigDecimal moneyToReturn = balance.getBalance();

        while(true){
            if(moneyToReturn.compareTo(quarterValue) == 0 || moneyToReturn.compareTo(quarterValue) == 1){
                moneyToReturn = moneyToReturn.subtract(quarterValue);
                numOfQuarters++;
            } else if (moneyToReturn.compareTo(dimeValue) == 0 || moneyToReturn.compareTo(dimeValue) == 1){
                moneyToReturn = moneyToReturn.subtract(dimeValue);
                numOfDimes++;
            } else if (moneyToReturn.compareTo(nickelValue) == 0 || moneyToReturn.compareTo(nickelValue) == 1){
                moneyToReturn = moneyToReturn.subtract(nickelValue);
                numOfNickels++;
            } else if (moneyToReturn.compareTo(pennyValue) == 0 || moneyToReturn.compareTo(pennyValue) == 1){
                moneyToReturn = moneyToReturn.subtract(pennyValue);
                numOfPennies++;
            } else {
                break;
            }
        }

        String restockItemsSql = "UPDATE items SET item_stock = 5 WHERE item_stock < 5;";
        jdbcTemplate.update(restockItemsSql);

        return String.format("Your change is %s quarters, %s dimes, %s nickels, and %s pennies", numOfQuarters, numOfDimes, numOfNickels, numOfPennies) ;
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
