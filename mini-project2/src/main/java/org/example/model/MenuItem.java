package org.example.model;

import java.sql.Timestamp;

public class MenuItem {
    private int itemId;
    private String name;
    private String description;
    private double price;
    private Category category;
    private boolean availability;
    private Timestamp createdAt;

    public enum Category {
        Starter,
        Main,
        Dessert,
        Drink
    }

    public MenuItem() {}

    public MenuItem(int itemId, String name, String description, double price,
                    Category category, boolean availability, Timestamp createdAt) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.availability = availability;
        this.createdAt = createdAt;
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "MenuItem{" + "itemId=" + itemId + ", name='" + name + '\'' +
                ", description='" + description + '\'' + ", price=" + price +
                ", category=" + category + ", availability=" + availability +
                ", createdAt=" + createdAt + '}';
    }
}
