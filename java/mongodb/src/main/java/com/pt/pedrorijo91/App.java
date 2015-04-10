package com.pt.pedrorijo91;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class App {
    public static void main(String[] args) {
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);

//            printDbsName(mongo);

//            printCollectionsName(mongo);

//            createDoc(mongo);

//            updateDoc(mongo);
//            updateDocInc(mongo);

//            findDoc(mongo);
//            findDocStar(mongo);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void findDocStar(MongoClient mongo) {
        DB db = mongo.getDB("test");

        DBCollection collection = db.getCollection("testCollection");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "mkyong-updated");

        DBCursor cursor = collection.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void findDoc(MongoClient mongo) {
        DB db = mongo.getDB("test");

        DBCollection collection = db.getCollection("testCollection");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "mkyong-updated");

        DBCursor cursor = collection.find(searchQuery);

        BasicDBObject allQuery = new BasicDBObject();
        BasicDBObject fields = new BasicDBObject();
        fields.put("age", 1);
        fields.put("createdDate", 1);
        fields.put("_id", 0);

        cursor = collection.find(allQuery, fields);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void updateDoc(MongoClient mongo) {
        DB db = mongo.getDB("test");

        DBCollection collection = db.getCollection("testCollection");

        BasicDBObject query = new BasicDBObject();
        query.put("name", "mkyong");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "mkyong-updated");

        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        collection.update(query, updateObj);
    }

    private static void updateDocInc(MongoClient mongo) {
        DB db = mongo.getDB("test");

        DBCollection collection = db.getCollection("testCollection");

        BasicDBObject query = new BasicDBObject();
        query.put("name", "mkyong-updated");

        BasicDBObject updateObj = new BasicDBObject().append("$inc", new BasicDBObject().append("age", 99));

        collection.update(query, updateObj);
    }

    private static void createDoc(MongoClient mongo) {
        DB db = mongo.getDB("test");

        DBCollection table = db.getCollection("testCollection");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "mkyong");
        document.put("age", 30);
        document.put("createdDate", new Date());
        table.insert(document);
    }

    private static void printCollectionsName(MongoClient mongo) {
        DB db = mongo.getDB("test");
        Set<String> tables = db.getCollectionNames();

        for (String coll : tables) {
            System.out.println(coll);
        }
    }

    private static void printDbsName(MongoClient mongo) {
        List<String> dbs = mongo.getDatabaseNames();
        for (String db : dbs) {
            System.out.println(db);
        }
    }
}
