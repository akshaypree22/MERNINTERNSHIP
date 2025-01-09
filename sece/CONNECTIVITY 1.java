2 <modelVersion>4.0.0</modelVersion>

<groupId>example.session</groupId>
<artifactId>mongo_session</artifactId>
<version>1.0-SNAPSHOT</version>

<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>

<dependencies>
    <dependency>
        <groupId>org.mongodb</groupId>
        <artifactId>mongodb-driver-sync</artifactId>
        <version>4.11.1</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>3.1.0</version>
            <configuration>
                <mainClass>example.session.Main</mainClass>
            </configuration>
        </plugin>
    </plugins>
</build>
</project>package example.session;

import com.mongodb.client.*;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        // Create a MongoDB client
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        
        // Get the database
        MongoDatabase database = mongoClient.getDatabase("college");
        
        // Get the collection
        MongoCollection<Document> collection = database.getCollection("students");
        
        // Create documents to insert
        Document student1 = new Document("name", "Harish revanth")
            .append("age", 20)
            .append("major", "Computer Science")
            .append("gpa", 3.8);

        Document student2 = new Document("name", "Janani")
            .append("age", 22)
            .append("major", "Engineering")
            .append("gpa", 3.9)
            .append("gender", "Female");
        
        // Insert documents
        collection.insertOne(student1);
        collection.insertOne(student2);
        
        System.out.println("Documents inserted successfully!");

        // List the documents
        FindIterable<Document> documents = collection.find();
        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }

        // Close the connection
        mongoClient.close();
    }
}