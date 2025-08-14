package org.example.service.interfaces;


import org.example.model.MenuItem;
import java.util.List;

public interface MenuItemService {
    void addMenuItem(MenuItem item);
    MenuItem getMenuItemById(int itemId);
    List<MenuItem> getAllMenuItems();
    void updateMenuItem(MenuItem item);
    void deleteMenuItem(int itemId);
}