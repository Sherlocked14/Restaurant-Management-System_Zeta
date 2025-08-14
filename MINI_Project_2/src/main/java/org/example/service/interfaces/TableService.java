package org.example.service.interfaces;

import org.example.model.Table;
import org.example.model.TableBooking;

import java.util.List;

public interface TableService {
    void addTable(Table table);
   Table getTableById(int tableId);
    List<Table> getAllTables();
    void updateTable(Table table);
    void deleteTable(int tableId);
    void bookTable(TableBooking tableBooking);
}
