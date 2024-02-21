import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySQLCRUD {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/Store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST888";
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createCustomer(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter id: ");
        String id = scanner.next();
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter age: ");
        String age = scanner.next();
        System.out.print("Enter address: ");
        String address = scanner.next();
        insertCustomer(connection, id, name, age, address);
    }

    public static void readCustomers(Connection connection) throws SQLException {
        List<Customer> customers = getAllCustomers(connection);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public static void updateCustomer(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter id of customer to update: ");
        String id = scanner.next();
        System.out.print("Enter new name: ");
        String name = scanner.next();
        updateCustomer(connection, id, name);
    }

    public static void deleteCustomer(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter id of customer to delete: ");
        int id = scanner.nextInt();
        deleteCustomer(connection, id);
    }

    public static void insertCustomer(Connection connection, String id, String name, String age, String address) throws SQLException {
        String sql = "INSERT INTO customers (id, name, age, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, address);
            preparedStatement.executeUpdate();
            System.out.println("Customer inserted successfully.");
        }
    }

    public static List<Customer> getAllCustomers(Connection connection) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, name, age, address FROM customers";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String address = resultSet.getString("address");
                customers.add(new Customer(id, name, age, address));
            }
        }
        return customers;
    }

    public static void updateCustomer(Connection connection, String id, String name) throws SQLException {
        String sql = "UPDATE customers SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteCustomer(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

public static void sqlMenu() {
    int choice;
    Connection connection = null;
    try {
        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    Scanner scanner = new Scanner((System.in));
    choice = Integer.parseInt((scanner.nextLine()));

    System.out.println("1. Create customer");
    System.out.println("2. Read customers");
    System.out.println("3. Update customers");
    System.out.println("4. Delete customers");
    System.out.println("0. Exit");
    System.out.print("Enter your choice: ");
    choice = Integer.parseInt((scanner.nextLine()));


    switch (choice) {
        case 1:
            try {
                createCustomer(connection, scanner);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;
        case 2:
            try {
                readCustomers(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;
        case 3:
            try {
                updateCustomer(connection, scanner);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;
        case 4:
            try {
                deleteCustomer(connection, scanner);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;
        case 0:
            System.out.println("Exiting...");
            break;
        default:
            System.out.println("Invalid choice!");
    }


}}