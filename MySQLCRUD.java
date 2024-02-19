import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class MySQLCRUD {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/School";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST888";

    public static void main(String[] args) {
    Connection connection = null;
    try {
     connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

    interStudent(connection, 1, "Jack", "Black", 40, "jackblack@example");

    List<Student> students = getAllStudents(connection);
    for (Student student : students) {
        System.out.println(student);
    }
    updateStudent(connection, 1, "Jake");

    students = getAllStudents(connection);
    for (Student student : students) {
        System.out.println(student);
    }
    deleteStudent(connection, 1);
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
    private static void interStudent(Connection connection, int id, String firstName, String lastName, int age, String email)
        throws SQLException{
        String sql = "INSTER INFO students ( id, firstName, lastName, age, email) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
        }
    }
    private static List<Student> getAllStudents(Connection connection) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, firstName, lastName, age, email FROM students";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                students.add(new Student(id, firstName, lastName, age, email));

            }
        }
        return students;
    }
    private static void updateStudent(Connection connection, int id, String newFirstName) throws SQLException {
        String sql = "UPDATE students SET firstName = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newFirstName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }
    private static void deleteStudent(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}

class Student {
    private int id;
    private String firstName;
    private String lastName;
    private  int age;
    private String email;

    public Student(int id, String firstName, String lastName, int age, String email){

    }
    @Override
    public String toString() {
        return "Student{" +
                "id= " + id +
                ", firstName=" + firstName +'\''+
                ", lastName+" + lastName + '\'' +
                "age=" + age +
                ", email= " + email + '\''+
                '}';
    }
}