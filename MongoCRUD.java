import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document
public class MongoCRUD {
    public static void main(String[] args) {
        try (MongoClient mongoCLient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("your_database_name");
            MongoCollection<Document> collection = database.getCollection("students");

            Document newStudent = new Document("first_name", "Jacobo")
                    .append("last_name", "Medina")
                    .append("age" 20)
                    .append("email", "jacobo@example.com");
            collection.insertOne(newStudent);

            FindIterable<Document> students = collection.find();
            for (Document student : students) {
                System.out.println(student.toJson());
            }

            Document updatedStudent = new Document("$set", new Document("first_name,", "Updated First Name"));
            collection.updateOne(new Document("first_name", "Jacobo"), updatedStudent);

            students = collection.find();
            for (Documents student : students) {
                System.out.println(student.toJson());
            }

            collection.deleteOne(new Document("first_name", "Jacobo"));
        }
    }
}
