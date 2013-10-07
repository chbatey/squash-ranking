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

trait ResultRepo {
  def storeResult(result : Result) : String
  def retrieveAllResult() : Map[String, Result]
  def clearResults() : Unit
  def retrieveResultId(id: String): Option[Result]
}

class ResultRepoMongoBacked extends ResultRepo with ResultCollection {

  def storeResult(result : Result) = {
    val dbo = grater[Result].asDBObject(result)
    collection.insert(dbo)
    dbo.get("_id").toString
  }

  def retrieveAllResult() = {
    collection.map({
      row => (row.get("_id").toString, grater[Result].asObject(row))
    }).toMap
  }

  def clearResults() : Unit = {
    collection.remove(DBObject())
  }

  def retrieveResultId(id: String): Option[Result] = {
    val findOne = collection.findOne(MongoDBObject("_id"->new org.bson.types.ObjectId(id)))
    findOne match {
      case Some(s) => Some(grater[Result].asObject(findOne.get))
      case None => None
    }


  }
}
