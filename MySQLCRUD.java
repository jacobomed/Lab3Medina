import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCRUD {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/Store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST888";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            insertCustomer(connection, "1", "Jack", "40", "1600 Woodland");

            List<Customer> customers = getAllCustomers(connection);
            for (Customer customer : customers) {
                System.out.println(customer);
            }

            updateCustomer(connection, "1", "Jake");

            customers = getAllCustomers(connection);
            for (Customer customer : customers) {
                System.out.println(customer);
            }

            //deleteCustomer(connection, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertCustomer(Connection connection, String id, String name, String age, String address)
            throws SQLException {
        String sql = "INSERT INTO customers (id, name, age, address) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, address);
            preparedStatement.executeUpdate();
        }
    }

    private static List<Customer> getAllCustomers(Connection connection) throws SQLException {
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

    private static void updateCustomer(Connection connection, String id, String name) throws SQLException {
        String sql = "UPDATE customers SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        }
    }

   // private static void deleteCustomer(Connection connection, int id) throws SQLException {
        //String sql = "DELETE FROM customers WHERE id = ?";
       // try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           // preparedStatement.setInt(1, id);
    //preparedStatement.executeUpdate();
       // }
    //}
}
