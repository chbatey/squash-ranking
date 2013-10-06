package com.lala.bambi.squash.dao

import com.lala.bambi.squash.domain.Result

import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

/**
 * Created with IntelliJ IDEA.
 * User: chbatey
 * Date: 06/10/2013
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
class ResultDao {
  val uri = MongoClientURI("mongodb://cloudbees:9157f5427c82800153723bc837149a55@paulo.mongohq.com:10013/aA2tRJeyaOYDeWWNkB5wg")
  val mongoClient = MongoClient(uri)
  val database = mongoClient(uri.database.get)
  val collection = database("results")

  def storeResult(result : Result) = {
    val dbo = grater[Result].asDBObject(result)
    collection.insert(dbo)
  }

  def retrieveAllResult() : List[Result] = {
    collection.map({
      row => grater[Result].asObject(row)
    }).toList
  }

  def clearResults() : Unit = {
    collection.remove(DBObject())
  }
}
