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

    insertStuddent(connection, 1, "Jack", "Black", 40, "jackblack@example");

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
    } catch (SQLException e)
    }
}
