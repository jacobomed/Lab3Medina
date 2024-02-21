import com.google.gson.Gson;
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

    public static void mongoMenu() {
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

    public static void createCustomer(MongoCollection<Document> collection) {
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

    public static void readCustomers(MongoCollection<Document> collection) {
        FindIterable<Document> customers = collection.find();
        for (Document customer : customers) {
            System.out.println(customer.toJson());
        }
    }

    public static void updateCustomer(MongoCollection<Document> collection) {
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

    public static void deleteCustomer(MongoCollection<Document> collection) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name to delete: ");
        String name = scanner.nextLine();

        Document filter = new Document("Name", name);
        collection.deleteOne(filter);
        System.out.println("Customer deleted successfully.");
    }
}
