package com.lala.bambi.squash.dao

import org.scalatest.{BeforeAndAfter, FunSpec}
import com.lala.bambi.squash.domain.{Result, Player}
import org.scalatest.matchers.ShouldMatchers
import com.mongodb.casbah.commons.MongoDBObject
import org.json4s.{DefaultFormats, Formats}

/**
 * Created with IntelliJ IDEA.
 * User: chbatey
 * Date: 06/10/2013
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
class ResultRepoMongoBackedTest extends FunSpec with BeforeAndAfter with ShouldMatchers with ResultCollection {
  var resultDao : ResultRepoMongoBacked = _
  before {
    resultDao = new ResultRepoMongoBacked
    collection.remove(MongoDBObject())
  }

  describe("Storing results in Mongo") {
    it("should store a result in mongo and be retrieveable by getting all results") {
      val result = new Result(new Player("lala", 8), new Player("bambi", 9))
      val id = resultDao.storeResult(result)

      val resultFromDB = resultDao.retrieveAllResult()

      resultFromDB.size should equal(1)
      resultFromDB(id) should equal(result)
    }

    it("should return an ID that can be used to retrieve") {
      val result1 = new Result(new Player("lala", 8), new Player("bambi", 9))

      val id = resultDao.storeResult(result1)
      val returnedResult = resultDao.retrieveResultId(id)

      returnedResult.get should equal(result1)
    }

    it("should be able to store more tha one and retrieve individually") {
      val result1 = new Result(new Player("lala", 8), new Player("bambi", 9))
      val result2 = new Result(new Player("mansy", 8), new Player("bambi", 9))
      val id1 = resultDao.storeResult(result1)
      val id2 = resultDao.storeResult(result2)

      val resultFromDB = resultDao.retrieveAllResult()

      resultFromDB.size should equal(2)
      resultFromDB(id1) should equal(result1)
      resultFromDB(id2) should equal(result2)
    }
  }

  describe("Removing all results in Mongo DB") {
    it("should remove everything in the results collections") {
      collection.insert(MongoDBObject("something" -> "anything"))
      collection.size should equal(1)

      resultDao.clearResults()
      collection.size should equal(0)
    }
  }
}
