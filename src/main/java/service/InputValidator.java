package service;

public class InputValidator {

    public static boolean isValidName(String name) {
        return name.matches("[A-Za-z ]+");
    }

    public static boolean isValidId(String id) {
        return id.matches("\\d+");
    }

    public static boolean isValidStatus(String status) {
        return status.matches("[A-Za-z ]+");
    }
}
