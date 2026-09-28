package repository;

import model.User;
import model.Student;
import model.Staff;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();
    private final String FILE_PATH = "users.txt";

    public UserRepository() {
        loadFromFile();
        // Ensure demo accounts exist
        if (users.isEmpty()) {
            users.add(new Student("student1", "pass123"));
            users.add(new Staff("staff1", "admin123"));
            saveToFile();
        }
    }

    public void addUser(User user) {
        users.add(user);
        saveToFile();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User findUser(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public boolean deleteUser(String username) {
        boolean removed = users.removeIf(u -> u.getUsername().equals(username));
        if (removed) saveToFile();
        return removed;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (User u : users) {
                writer.println(u.getUsername() + "," + u.getPassword() + "," + u.getRole());
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    if (parts[2].equals("Student")) {
                        users.add(new Student(parts[0], parts[1]));
                    } else if (parts[2].equals("Staff")) {
                        users.add(new Staff(parts[0], parts[1]));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No previous user data found.");
        }
    }
}
