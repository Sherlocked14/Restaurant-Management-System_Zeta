package org.example.service.impl;

import org.example.model.Table;
import org.example.model.TableBooking;
import org.example.service.interfaces.TableService;
import java.util.ArrayList;
import java.util.List;

public class TableServiceImpl implements TableService {
    private List<Table> tables = new ArrayList<>();

    @Override
    public void addTable(Table table) {
        tables.add(table);
    }

    @Override
    public Table getTableById(int tableId) {
        return tables.stream().filter(t -> t.getTableId() == tableId).findFirst().orElse(null);
    }

    @Override
    public List<Table> getAllTables() {
        return tables;
    }

    @Override
    public void updateTable(Table table) {
        deleteTable(table.getTableId());
        addTable(table);
    }

    @Override
    public void deleteTable(int tableId) {
        tables.removeIf(t -> t.getTableId() == tableId);
    }

    @Override
    public void bookTable(TableBooking tableBooking) {
        // Implementation for booking a table
        Table table = getTableById(tableBooking.getTableId());
        if (table != null && table.getStatus() == Table.Status.Available) {
            table.setStatus(Table.Status.Booked);
        }
    }
}
