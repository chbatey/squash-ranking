package com.lala.bambi.squash.dao

import com.mongodb.casbah.Imports._

trait ResultCollection {
  val uri = MongoClientURI("mongodb://cloudbees:9157f5427c82800153723bc837149a55@paulo.mongohq.com:10013/aA2tRJeyaOYDeWWNkB5wg")
  //val uri = MongoClientURI("mongodb://localhost:27017/squash-ranking")
  val mongoClient = MongoClient(uri)
  val database = mongoClient(uri.database.get)
  val resultsCollections = database("results")
}
