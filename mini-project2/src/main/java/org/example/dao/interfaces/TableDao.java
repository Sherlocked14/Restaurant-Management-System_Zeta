package org.example.dao.interfaces;

import org.example.model.Table;
import java.util.List;

public interface TableDao {
    void addTable(Table table);
    Table getTableById(int tableId);
    List<Table> getAllTables();
    void updateTable(Table table);
    void deleteTable(int tableId);
}
