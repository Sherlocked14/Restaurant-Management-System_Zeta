package org.example.dao.interfaces;

import org.example.model.MenuItem;
import java.util.List;

public interface MenuItemDao {
    void addMenuItem(MenuItem menuItem);
    MenuItem getMenuItemById(int itemId);
    List<MenuItem> getAllMenuItems();
    void updateMenuItem(MenuItem menuItem);
    void deleteMenuItem(int itemId);
}
