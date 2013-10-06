package com.lala.bambi.squash

import com.lala.bambi.squash.domain.{Player, Result}
import grizzled.slf4j.Logging
import org.scalatra.{Ok, NotFound}
import com.lala.bambi.squash.dao.ResultDao

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

class SquashRankingServlet extends SquashRankingStack with JacksonJsonSupport with Logging {

  protected implicit val jsonFormats: Formats = DefaultFormats
  val resultsDao : ResultDao = new ResultDao

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
    info("unparsed body " + request.body)
    val body = parsedBody.extract[Result]
    info("Body:: " + body)
    resultsDao.storeResult(body)
  }

  get("/results/:id") {
    val id = params("id")
    if (id == "1") NotFound("I dont know about 1!!")
    else Ok(Result(new Player("bambi", 9), new Player("lala", 7)))
  }

}
