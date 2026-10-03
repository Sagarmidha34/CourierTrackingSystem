package service;

import model.Courier;
import structure.CourierLinkedList;
import util.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class CourierService {
    static Scanner scanner = new Scanner(System.in);
    static CourierLinkedList list = new CourierLinkedList();

    public static void addCourier() {
        System.out.print("Enter Sender Name: ");
        String sender = scanner.nextLine();
        while (!InputValidator.isValidName(sender)) {
            System.out.print("Invalid. Enter alphabetic sender name only: ");
            sender = scanner.nextLine();
        }

        System.out.print("Enter Receiver Name: ");
        String receiver = scanner.nextLine();
        while (!InputValidator.isValidName(receiver)) {
            System.out.print("Invalid. Enter alphabetic receiver name only: ");
            receiver = scanner.nextLine();
        }

        System.out.print("Enter Origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter Status: ");
        String status = scanner.nextLine();
        while (!InputValidator.isValidStatus(status)) {
            System.out.print("Invalid. Enter alphabetic status only: ");
            status = scanner.nextLine();
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO couriers (sender, receiver, origin, destination, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sender);
            ps.setString(2, receiver);
            ps.setString(3, origin);
            ps.setString(4, destination);
            ps.setString(5, status);
            ps.executeUpdate();
            System.out.println("Courier added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewAllCouriers() {
        list.clear();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM couriers";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Courier c = new Courier(rs.getInt("id"), rs.getString("sender"),
                        rs.getString("receiver"), rs.getString("origin"),
                        rs.getString("destination"), rs.getString("status"));
                list.add(c);
            }
            list.printAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCourier() {
        System.out.print("Enter Courier ID to update: ");
        String idStr = scanner.nextLine();
        while (!InputValidator.isValidId(idStr)) {
            System.out.print("Invalid. Enter numeric ID only: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        System.out.print("Enter new status: ");
        String status = scanner.nextLine();
        while (!InputValidator.isValidStatus(status)) {
            System.out.print("Invalid. Enter alphabetic status only: ");
            status = scanner.nextLine();
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE couriers SET status=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Courier status updated.");
            } else {
                System.out.println("Courier not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCourier() {
        System.out.print("Enter Courier ID to delete: ");
        String idStr = scanner.nextLine();
        while (!InputValidator.isValidId(idStr)) {
            System.out.print("Invalid. Enter numeric ID only: ");
            idStr = scanner.nextLine();
        }
        int id = Integer.parseInt(idStr);

        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM couriers WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Courier deleted.");
            } else {
                System.out.println("Courier not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
