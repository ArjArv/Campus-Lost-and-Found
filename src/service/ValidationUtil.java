package service;

public class ValidationUtil {
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }
}
