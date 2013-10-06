package com.lala.bambi.squash

import com.lala.bambi.squash.domain.Result
import grizzled.slf4j.Logging

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

class SquashRankingServlet extends SquashRankingStack with JacksonJsonSupport with Logging {

  protected implicit val jsonFormats: Formats = DefaultFormats

  // This implies we will always respond with JSON
  before() {
    contentType = formats("json")
  }

  get("/") {
    """ {"appName":"squash-ranking"} """
  }

  post("/result") {
    // Here is what we did to get the body as a string before we added JSON support
    //val body = request.body

    //Now we just let Scalatra parse the json for us
    val body = parsedBody.extract[Result]
    info("Body:: " + body)
  }

}
