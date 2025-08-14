package org.example.service.impl;



import org.example.model.MenuItem;
import org.example.service.interfaces.MenuItemService;
import java.util.ArrayList;
import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {
    private List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    @Override
    public MenuItem getMenuItemById(int itemId) {
        return menuItems.stream().filter(i -> i.getItemId() == itemId).findFirst().orElse(null);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    @Override
    public void updateMenuItem(MenuItem item) {
        deleteMenuItem(item.getItemId());
        addMenuItem(item);
    }

    @Override
    public void deleteMenuItem(int itemId) {
        menuItems.removeIf(i -> i.getItemId() == itemId);
    }
}
