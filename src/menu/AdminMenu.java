package menu;

import service.UserService;
import service.ItemService;
import service.RecordNotFoundException;
import service.InvalidInputException;
import java.util.Scanner;

public class AdminMenu {
    private UserService userService = new UserService();
    private ItemService itemService = new ItemService();
    private Scanner sc = new Scanner(System.in);

    public void showAdminMenu(String username) {
        int choice;
        do {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. Verify Item (Update Status)");
            System.out.println("2. Delete Item");
            System.out.println("3. List All Items");
            System.out.println("4. Manage Users (Add/Delete/List)");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Item ID to verify: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new status (Verified/Claimed): ");
                        String status = sc.nextLine();
                        itemService.updateItemStatus(id, status);
                    }
                    case 2 -> {
                        System.out.print("Enter Item ID to delete: ");
                        int id = sc.nextInt();
                        itemService.deleteItem(id);
                    }
                    case 3 -> itemService.listItems();
                    case 4 -> {
                        System.out.println("a) Add User");
                        System.out.println("b) Delete User");
                        System.out.println("c) List Users");
                        System.out.print("Enter choice: ");
                        String subChoice = sc.nextLine();
                        switch (subChoice) {
                            case "a" -> {
                                System.out.print("Username: ");
                                String uname = sc.nextLine();
                                System.out.print("Password: ");
                                String pwd = sc.nextLine();
                                System.out.print("Role (Student/Staff): ");
                                String role = sc.nextLine();
                                userService.registerUser(uname, pwd, role);
                            }
                            case "b" -> {
                                System.out.print("Enter username to delete: ");
                                String uname = sc.nextLine();
                                userService.deleteUser(uname);
                            }
                            case "c" -> userService.listUsers();
                            default -> System.out.println("Invalid choice!");
                        }
                    }
                    case 5 -> System.out.println("Exiting Admin Dashboard...");
                    default -> System.out.println("Invalid choice!");
                }
            } catch (InvalidInputException | RecordNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);
    }
}
