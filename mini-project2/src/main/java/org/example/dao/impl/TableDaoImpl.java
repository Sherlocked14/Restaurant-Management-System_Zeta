package org.example.dao.impl;

import org.example.dao.interfaces.TableDao;
import org.example.model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDaoImpl implements TableDao {
    private final Connection connection;

    public TableDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTable(Table table) {
        String sql = "INSERT INTO tables (table_number, capacity, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, table.getTableNumber());
            stmt.setInt(2, table.getCapacity());
            stmt.setString(3, table.getStatus().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Table getTableById(int tableId) {
        String sql = "SELECT * FROM tables WHERE table_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tableId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Table> getAllTables() {
        String sql = "SELECT * FROM tables";
        List<Table> tables = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tables.add(mapRowToTable(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public void updateTable(Table table) {
        String sql = "UPDATE tables SET table_number=?, capacity=?, status=? WHERE table_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, table.getTableNumber());
            stmt.setInt(2, table.getCapacity());
            stmt.setString(3, table.getStatus().toString());
            stmt.setInt(4, table.getTableId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTable(int tableId) {
        String sql = "DELETE FROM tables WHERE table_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tableId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Table mapRowToTable(ResultSet rs) throws SQLException {
        Table table = new Table();
        table.setTableId(rs.getInt("table_id"));
        table.setTableNumber(rs.getInt("table_number"));
        table.setCapacity(rs.getInt("capacity"));
        table.setStatus(Table.Status.valueOf(rs.getString("status")));
        return table;
    }
}
