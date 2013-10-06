package com.lala.bambi.squash.dao;

import org.scalatest.{BeforeAndAfter, FunSpec}
import com.lala.bambi.squash.domain.{Result, Player}
import org.scalatest.matchers.ShouldMatchers
;

/**
 * Created with IntelliJ IDEA.
 * User: chbatey
 * Date: 06/10/2013
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
class ResultDaoTest extends FunSpec with BeforeAndAfter with ShouldMatchers {
  var resultDao : ResultDao = _
  before {
    resultDao = new ResultDao
    resultDao.clearResults()
  }

  describe("Storing results in Mongo") {
    it("should store a result in mongo") {
      val result = new Result(new Player("lala", 8), new Player("bambi", 9))
      resultDao.storeResult(result)

      val resultFromDB = resultDao.retrieveAllResult()
      resultFromDB.size should equal(1)
      resultFromDB(0) should equal(result)
    }
  }
}
