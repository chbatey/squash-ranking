package com.lala.bambi.squash.web

import com.escalatesoft.subcut.inject.NewBindingModule
import com.lala.bambi.squash.dao.{ResultRepoMongoBacked, ResultRepo}
import com.lala.bambi.squash.domain.{Player, Result}

/**
 * Created with IntelliJ IDEA.
 * User: chbatey
 * Date: 06/10/2013
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
object StubbedProjectConfiguration extends NewBindingModule(module => {
  import module._

  bind [ResultRepo] toSingle new ResultRepo {
    var results: scala.collection.mutable.Map[String, Result] = scala.collection.mutable.Map()
    results("0") = new Result(new Player("chris", 5), new Player("lloyd", 9))

    def clearResults(): Unit = {}

    def retrieveAllResult() : Map[String, Result] = {
      results.toMap
    }

    def storeResult(result: Result) : String = {
      val key = results.size.toString
      results(key) = result
      key
    }

    def retrieveResultId(id: String): Option[Result] = {
      if (results.contains(id)) Some(results(id))
      else None
    }
  }
})
