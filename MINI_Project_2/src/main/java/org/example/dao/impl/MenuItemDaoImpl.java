package org.example.dao.impl;

import org.example.dao.interfaces.MenuItemDao;
import org.example.model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDaoImpl implements MenuItemDao {
    private final Connection connection;

    public MenuItemDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO menu_items (name, description, price, category, is_available) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, menuItem.getName());
            stmt.setString(2, menuItem.getDescription());
            stmt.setDouble(3, menuItem.getPrice());
            stmt.setString(4, menuItem.getCategory().toString());
            stmt.setBoolean(5, menuItem.isAvailability());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuItem getMenuItemById(int itemId) {
        String sql = "SELECT * FROM menu_items WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapRowToMenuItem(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        String sql = "SELECT * FROM menu_items";
        List<MenuItem> items = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) items.add(mapRowToMenuItem(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void updateMenuItem(MenuItem menuItem) {
        String sql = "UPDATE menu_items SET name=?, description=?, price=?, category=?, is_available=? WHERE item_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, menuItem.getName());
            stmt.setString(2, menuItem.getDescription());
            stmt.setDouble(3, menuItem.getPrice());
            stmt.setString(4, menuItem.getCategory().toString());
            stmt.setBoolean(5, menuItem.isAvailability());
            stmt.setInt(6, menuItem.getItemId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenuItem(int itemId) {
        String sql = "DELETE FROM menu_items WHERE item_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MenuItem mapRowToMenuItem(ResultSet rs) throws SQLException {
        MenuItem item = new MenuItem();
        item.setItemId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getDouble("price"));
        item.setCategory(MenuItem.Category.valueOf(rs.getString("category")));
        // Set the availability status
        item.setAvailability(rs.getBoolean("is_available"));
        return item;
    }
}
