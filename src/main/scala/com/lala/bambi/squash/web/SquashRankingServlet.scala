package com.lala.bambi.squash.web

import com.lala.bambi.squash.domain.{Result}
import grizzled.slf4j.Logging
import com.lala.bambi.squash.dao.{ResultRepo, ResultRepoMongoBacked}
import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import com.lala.bambi.squash.SquashRankingStack
import org.scalatra.NotFound

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

class SquashRankingServlet(implicit val bindingModule: BindingModule)
  extends SquashRankingStack with JacksonJsonSupport with Logging with Injectable {

  protected implicit val jsonFormats: Formats = DefaultFormats
  val resultsDao : ResultRepo = inject[ResultRepo]

  // This implies we will always respond with JSON
  before() {
    contentType = formats("json")
  }

  get("/") {
    """ {"appName":"squash-ranking"} """
  }

  post("/results") {
    // Here is what we did to get the body as a string before we added JSON support
    //val body = request.body

    //Now we just let Scalatra parse the json for us
    val result = parsedBody.extract[Result]
    info("Request to create:: " + result)
    resultsDao.storeResult(result)
  }

  get("/results/:id") {
    val id = params("id")
    val result = resultsDao.retrieveResultId(id)
    result match {
      case Some(s) => s
      case None => NotFound()
    }
  }
}
