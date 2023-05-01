package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Bank;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcBankDao implements BankDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBankDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Bank viewTotal(){
        Bank balance = new Bank();
        String sql = "SELECT balance_total FROM balance;";

        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);

        if (results.next()){
            balance = mapToBank(results);
        }
        return balance;
    }

    private Bank mapToBank(SqlRowSet rowSet){
        Bank balance = new Bank();

        balance.setTotal(rowSet.getBigDecimal("balance_total"));

        return balance;
    }
}
