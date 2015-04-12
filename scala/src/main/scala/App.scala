import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject

/**
 * Created by pedrorijo on 11/04/15.
 */
object App extends App {

  println("Hello world")

  val mongoClient = MongoClient("localhost", 27017)

  val db = mongoClient("test")
  db.collectionNames.foreach(println(_))


  val coll = db("testColl")
  coll.drop

  val a = MongoDBObject("hello" -> "world")
  val b = MongoDBObject("language" -> "scala")
  val c = MongoDBObject("xpto" -> "yes")

  coll insert a
  coll insert b
  coll insert c

  println(coll.count())

  val allDocs = coll.find()
  for(doc <- allDocs) println( doc )


  val hello = MongoDBObject("hello" -> "world")
  val helloWorld = coll.findOne( hello )

  // Find a document that doesn't exist
  val goodbye = MongoDBObject("goodbye" -> "world")
  val goodbyeWorld = coll.findOne( goodbye )

  // Find and display desired fields
  val helloSelect = MongoDBObject("_id" -> 0, "hello" -> 1)
  val helloWorldSelect= coll.findOne(hello, helloSelect )

  println(helloWorld)
  println(goodbyeWorld)
  println(helloWorldSelect)

  val query = MongoDBObject("language" -> "scala")
  val update = MongoDBObject("platform" -> "JVM")
  val result = coll.update( query, update )

  println("Number updated: " + result.getN)
  for (c <- coll.find) println(c)

  val query2 = MongoDBObject("platform" -> "JVM")
  val update2 = MongoDBObject("$set" -> MongoDBObject("language" -> "Scala"))

  val result2 = coll.update( query2, update2 )

  println( "Number updated: " + result2.getN )
  for ( c <- coll.find ) println( c )


}
