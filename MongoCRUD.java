import com.google.gson.Gson;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import javax.print.Doc;


public class MongoCRUD {
        public static void main(String[] args) {
            try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                // Access the database and collection
                MongoDatabase database = mongoClient.getDatabase("ShoeStore");
                MongoCollection<Document> collection = database.getCollection("Customer");

                Gson gson = new Gson();
                Customer customer = new Customer("101", "John Doe", "john@example", "123 Street rd");
                String jcustomer = gson.toJson(customer);

                // Example: Insert a document
                Document document = Document.parse(jcustomer);
                collection.insertOne(document);

                // Read
                FindIterable<Document> customers = collection.find();
                for (Document cust : customers) {
                    System.out.println(cust.toJson());
                }

                // Update

                //Document updatedCustomer = new Document("$set", new Document("Silhouette", "Updated Silhouette"));
                //collection.updateOne(new Document("Silhouette", "Jordan 1"), updatedCustomer);

                // Read again
                customers = collection.find();
                for (Document cust : customers) {
                    System.out.println(cust.toJson());
                }

                // Delete
                collection.deleteOne(new Document("Sihlouette", "Jordan 1"));

            }
        }
    }

