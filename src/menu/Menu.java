package menu;

import service.ItemService;
import service.InvalidInputException;
import service.RecordNotFoundException;
import java.util.Scanner;

public class Menu {
    private ItemService service = new ItemService();
    private Scanner sc = new Scanner(System.in);

    public void showMenu(String username) {
        int choice;
        do {
            System.out.println("\n--- Lost & Found Dashboard ---");
            System.out.println("1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. Search Item by ID");
            System.out.println("4. Search Item by Keyword");
            System.out.println("5. Update/Claim Item Status");
            System.out.println("6. Delete Item");
            System.out.println("7. List All Items");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Item Name: ");
                        String name = sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Last Seen Location: ");
                        String loc = sc.nextLine();
                        service.reportItem(name, desc, loc, username, "Lost");
                    }
                    case 2 -> {
                        System.out.print("Item Name: ");
                        String name = sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Found Location: ");
                        String loc = sc.nextLine();
                        service.reportItem(name, desc, loc, username, "Found");
                    }
                    case 3 -> {
                        System.out.print("Enter Item ID: ");
                        int id = sc.nextInt();
                        service.searchItemById(id);
                    }
                    case 4 -> {
                        System.out.print("Enter keyword to search: ");
                        String keyword = sc.nextLine();
                        service.searchItemByKeyword(keyword);
                    }
                    case 5 -> {
                        System.out.print("Enter Item ID to update: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new status (Claimed/Verified): ");
                        String status = sc.nextLine();
                        service.updateItemStatus(id, status);
                    }
                    case 6 -> {
                        System.out.print("Enter Item ID to delete: ");
                        int id = sc.nextInt();
                        service.deleteItem(id);
                    }
                    case 7 -> service.listItems();
                    case 8 -> {
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (InvalidInputException | RecordNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 8);
    }
}
