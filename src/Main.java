import menu.Menu;
import menu.AdminMenu;
import model.User;
import service.UserService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("=== Campus Lost and Found System ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        User loggedIn = null;
        if (choice == 1) {
            System.out.print("Enter username: ");
            String uname = sc.nextLine();
            System.out.print("Enter password: ");
            String pwd = sc.nextLine();
            loggedIn = userService.login(uname, pwd);
        } else if (choice == 2) {
            System.out.print("Enter new username: ");
            String uname = sc.nextLine();
            System.out.print("Enter new password: ");
            String pwd = sc.nextLine();
            System.out.print("Enter role (Student/Staff): ");
            String role = sc.nextLine();
            try {
                userService.registerUser(uname, pwd, role);
                loggedIn = userService.login(uname, pwd);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        if (loggedIn != null) {
            System.out.println("Login successful! Role: " + loggedIn.getRole());
            if (loggedIn.getRole().equals("Staff")) {
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.showAdminMenu(loggedIn.getUsername());
            } else {
                Menu menu = new Menu();
                menu.showMenu(loggedIn.getUsername());
            }
        } else {
            System.out.println("Invalid credentials or registration failed!");
        }

        sc.close();
    }
}
