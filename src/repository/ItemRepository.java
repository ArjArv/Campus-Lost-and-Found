package repository;

import model.Item;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private List<Item> items = new ArrayList<>();
    private final String FILE_PATH = "backup.txt";

    public ItemRepository() {
        loadFromFile();
    }

    public void addItem(Item item) {
        items.add(item);
        saveToFile();
    }

    public List<Item> getAllItems() {
        return items;
    }

    public Item searchById(int id) {
        for (Item item : items) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    public List<Item> searchByKeyword(String keyword) {
        List<Item> results = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                item.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                item.getLocation().toLowerCase().contains(keyword.toLowerCase()) ||
                item.getOwner().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    public boolean deleteItem(int id) {
        boolean removed = items.removeIf(item -> item.getId() == id);
        if (removed) saveToFile();
        return removed;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Item item : items) {
                writer.println(item.getId() + "," + item.getName() + "," + item.getDescription() + "," +
                               item.getLocation() + "," + item.getOwner() + "," + item.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Item item = new Item(parts[1], parts[2], parts[3], parts[4], parts[5]);
                    items.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
}
