import service.CourierService;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String role = login(scanner);

        if (role == null) {
            System.out.println("Login failed.");
            return;
        }

        if (role.equals("admin")) {
            runAdminControls(scanner);
        } else if (role.equals("user")) {
            runUserControls(scanner);
        } else {
            System.out.println("Invalid role.");
        }
    }

    private static String login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT role FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void runAdminControls(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Courier\n2. View All Couriers\n3. Update Courier\n4. Delete Courier\n5. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> CourierService.addCourier();
                case 2 -> CourierService.viewAllCouriers();
                case 3 -> CourierService.updateCourier();
                case 4 -> CourierService.deleteCourier();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void runUserControls(Scanner scanner) {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View All Couriers\n2. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> CourierService.viewAllCouriers();
                case 2 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
