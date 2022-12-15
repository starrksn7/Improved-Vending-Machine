package com.ImprovedVendingMachine.improvedvendingmachine.dao;

import com.ImprovedVendingMachine.improvedvendingmachine.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItemDao implements ItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Item> listAllItems() {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT location_code, item_name, item_cost, item_type, item_stock FROM items;";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Item nextItem = this.mapRowToItem(results);
            items.add(nextItem);
        }
        return items;
    }

    public int checkStock(String itemLocation) {
        Item item = new Item();

        String sql = "SELECT location_code, item_name, item_cost, item_type, item_stock FROM items " +
                "WHERE location_code = ?;";

        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, itemLocation);

        if (results.next()) {
            item = mapRowToItem(results);
        }

        return item.getItemStock();
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
}