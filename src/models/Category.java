package models;

import java.util.ArrayList;
import java.util.List;

public class Category {
    final String code;
    private String description;
    final List<ContentItem> items;

    public Category(String code, String description) {
        this.items = new ArrayList<>();
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<ContentItem> getItems() { return items; }

    public void addItem(ContentItem item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return code + " - " + description + " (Items: " + items.size() + ")";
    }
}