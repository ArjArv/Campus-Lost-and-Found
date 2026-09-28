package service;

import model.Item;
import repository.ItemRepository;
import java.util.List;

public class ItemService {
    private ItemRepository repo = new ItemRepository();

    public void reportItem(String name, String desc, String loc, String owner, String status)
            throws InvalidInputException {
        if (!ValidationUtil.isValidString(name) || !ValidationUtil.isValidString(desc)) {
            throw new InvalidInputException("Item name and description cannot be empty.");
        }
        Item item = new Item(name, desc, loc, owner, status);
        repo.addItem(item);
        System.out.println("Item reported successfully: " + item);
    }

    public void searchItemById(int id) throws RecordNotFoundException {
        Item item = repo.searchById(id);
        if (item != null) {
            System.out.println("Found: " + item);
        } else {
            throw new RecordNotFoundException("No item found with ID " + id);
        }
    }

    public void searchItemByKeyword(String keyword) {
        List<Item> results = repo.searchByKeyword(keyword);
        if (results.isEmpty()) {
            System.out.println("No items match your search.");
        } else {
            results.forEach(System.out::println);
        }
    }

    public void updateItemStatus(int id, String newStatus) throws RecordNotFoundException {
        Item item = repo.searchById(id);
        if (item != null) {
            item.setStatus(newStatus);
            repo.saveToFile();
            System.out.println("Item status updated to: " + newStatus);
        } else {
            throw new RecordNotFoundException("Item not found.");
        }
    }

    public void deleteItem(int id) throws RecordNotFoundException {
        if (repo.deleteItem(id)) {
            System.out.println("Item deleted successfully.");
        } else {
            throw new RecordNotFoundException("Item not found.");
        }
    }

    public void listItems() {
        repo.getAllItems().forEach(System.out::println);
    }
}
