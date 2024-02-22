import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import com.google.gson.Gson;

import java.util.Scanner;

public class RedisCRUD {

    /**
     * Jacobo Medina
     */
    public static void main(String[] args) {

        /**
         * This is the main function
         */

        try {
            // Your code for setting up Redis and performing initial operations

        } catch (JedisConnectionException e) {
            System.out.println("Could not connect to Redis: " + e.getMessage());
        }

        redisMenu(); // Call redisMenu() only once, outside of any loops
    }

    /**
     * Jacobo Medina
     */
    public static void redisMenu() {

    /**
     * This is the menu display and options for the Redis menu. It contains all the code neccessary to create, read, update
     * new info
     */

        int choice;
        Scanner scanner = new Scanner(System.in);
        Jedis jedis = new Jedis("localhost", 6379);
        Gson gson = new Gson();
        Customer customer = null; // Initialize customer

        try {
            System.out.println("1. Create customer");
            System.out.println("2. Read customers");
            System.out.println("3. Update customers");
            System.out.println("4. Delete customers");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Create customer");
                    customer = new Customer("101", "John Doe", "john@example", "123 Street rd");
                    String jcustomer = gson.toJson(customer);
                    jedis.set(customer.getId(), jcustomer);
                    break;
                case 2:
                    System.out.println("Read Customers");
                    String customerId = scanner.nextLine(); // Read customer ID
                    String value = jedis.get(customerId);
                    System.out.println(value);
                    break;
                case 3:
                    System.out.println("Update customers");
                    System.out.print("Enter id of customer to update: ");
                    String customerIdToUpdate = scanner.nextLine(); // Read customer ID
                    customer = new Customer(customerIdToUpdate, "Joe Doe", "joe@example", "456 Main St"); // Update with new info
                    String updatedCustomer = gson.toJson(customer);
                    jedis.set(customerIdToUpdate, updatedCustomer);
                    break;
                case 4:
                    System.out.println("Delete customer");
                    System.out.print("Enter id of customer to delete: ");
                    String customerIdToDelete = scanner.nextLine(); // Read customer ID
                    jedis.del(customerIdToDelete);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        jedis.close(); // Close jedis outside the loop
    }
}