import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;


public class MongoCRUD {
    public class StudentMongoCRUDExample {
        public static void main(String[] args) {
            // Create a MongoClient using the factory method
            try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                // Access the database and collection
                MongoDatabase database = mongoClient.getDatabase("your_database_name");
                MongoCollection<Document> collection = database.getCollection("shoes");

                // Example: Insert a document
                Document newShoe = new Document("Silhouette", "Presto")
                        .append("Brand", "Nike")
                        .append("Size", 12)
                        .append("Collection", "Off-White");
                collection.insertOne(newShoe);

                // Read
                FindIterable<Document> shoes = collection.find();
                for (Document shoe : shoes) {
                    System.out.println(shoe.toJson());
                }

                // Update
                Document updatedStudent = new Document("$set", new Document("Silhouette", "Updated Silhouette"));
                collection.updateOne(new Document("Silhouette", "Jordan 1"), updatedStudent);

                // Read again
                shoes = collection.find();
                for (Document shoe : shoes) {
                    System.out.println(shoe.toJson());
                }

                // Delete
                collection.deleteOne(new Document("Sihlouette", "Jordan 1"));

            }
        }
    }
}
