package service;

import model.Student;
import model.Staff;
import model.User;
import repository.UserRepository;

public class UserService {
    private UserRepository repo = new UserRepository();

    public void registerUser(String username, String password, String role) throws InvalidInputException {
        if (!ValidationUtil.isValidString(username) || !ValidationUtil.isValidString(password)) {
            throw new InvalidInputException("Username and password cannot be empty.");
        }
        if (role.equalsIgnoreCase("Student")) {
            repo.addUser(new Student(username, password));
        } else if (role.equalsIgnoreCase("Staff")) {
            repo.addUser(new Staff(username, password));
        } else {
            throw new InvalidInputException("Invalid role. Must be Student or Staff.");
        }
        System.out.println("User registered successfully: " + username + " (" + role + ")");
    }

    public User login(String username, String password) {
        return repo.findUser(username, password);
    }

    public void deleteUser(String username) {
        if (repo.deleteUser(username)) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void listUsers() {
        repo.getAllUsers().forEach(u -> System.out.println(u.getUsername() + " (" + u.getRole() + ")"));
    }
}
