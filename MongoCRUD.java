/** Project: IST242Lab3
 * Purpose Details: TO create CRUD operations for different databases
 * Course: IST242
 * Author: Jacobo Medina
 * Date Developed:2-18-2024
 * Last Date Changed: 2-21-2024
 * Rev: latest update 2-21-2024

 */
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.Scanner;

public class MongoCRUD {

    public static void main(String[] args) {
        mongoMenu();
    }
    /**
     * Jacobo Medina
     */

    public static void mongoMenu() {


        /**
         * The mongoMenu class is used to display the options of the mongo menu. This is where user gets to select
         * what CRUD operation they wish to operate
         */
        int choice;
        Scanner scanner = new Scanner(System.in);
        MongoClient mongoClient = null;
        MongoCollection<Document> collection = null;

        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("ShoeStore");
            collection = database.getCollection("Customer");

            do {
                System.out.println("1. Create customer");
                System.out.println("2. Read customers");
                System.out.println("3. Update customers");
                System.out.println("4. Delete customers");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        createCustomer(collection);
                        break;
                    case 2:
                        readCustomers(collection);
                        break;
                    case 3:
                        updateCustomer(collection);
                        break;
                    case 4:
                        deleteCustomer(collection);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 0);
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
            scanner.close();
        }
    }

    /**
     * Jacobo Medina
     */

    public static void createCustomer(MongoCollection<Document> collection) {

        /**
         * The CreateCustomer class is used to create customers. This is where user gets to input the parameters to
         * create a new customer into the database
         */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        Document customerDocument = new Document("ID", id)
                .append("Name", name)
                .append("Email", email)
                .append("Address", address);

        collection.insertOne(customerDocument);
        System.out.println("Customer created successfully.");
    }

    /**
     * Jacobo Medina
     */

    public static void readCustomers(MongoCollection<Document> collection) {

        /**
         * The readCustomer class is used to read customers data. This is where user gets the info on their selected
         * customer
         */

        FindIterable<Document> customers = collection.find();
        for (Document customer : customers) {
            System.out.println(customer.toJson());
        }
    }


    /**
     * Jacobo Medina
     */

    public static void updateCustomer(MongoCollection<Document> collection) {

        /**
         * The updateCustomer class is used to update customers data. This is where user gets to update the names of the
         * selected customer
         */


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name to update: ");
        String oldName = scanner.nextLine();
        System.out.print("Enter new customer name: ");
        String newName = scanner.nextLine();

        Document filter = new Document("Name", oldName);
        Document update = new Document("$set", new Document("Name", newName));
        collection.updateOne(filter, update);
        System.out.println("Customer updated successfully.");
    }


    /**
     * Jacobo Medina
     */
    public static void deleteCustomer(MongoCollection<Document> collection) {

        /**
         * The deleteCustomer class is used to delete customers data. This is where user gets to delete the
         * selected customer
         */


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name to delete: ");
        String name = scanner.nextLine();

        Document filter = new Document("Name", name);
        collection.deleteOne(filter);
        System.out.println("Customer deleted successfully.");
    }
}
