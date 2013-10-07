package com.lala.bambi.squash.web

import org.scalatest.FunSuite
import org.scalatra.test.scalatest.ScalatraSuite
import org.json4s._
import org.json4s.jackson.JsonMethods._
import com.lala.bambi.squash.domain._

class SquashRankingServletSpec extends ScalatraSuite with FunSuite {

  implicit val jsonFormats: Formats = DefaultFormats
  implicit val bindingModule = StubbedProjectConfiguration
  addServlet(new SquashRankingServlet, "/*")
  
  val preCannedResult = new Result(new Player("chris", 5), new Player("lloyd", 9))

  test("get result") {
    get("/results/0", headers = Map("Accept"->"application/json")) {
      status should equal (200)
      val result0 = parse(body).extract[Result]
      result0 should equal(preCannedResult)
    }

    get("/results/1", headers = Map("Accept"->"application/json")) {
      status should equal (404)
    }
  }

  test("create a result") {
    post("/results", body=""" {"player1": {"name":"lala", "score":7}, "player2": {"name":"bambi", "score":9} } """,
          headers = Map("Content-Type" -> "application/json")) {
      println(body)
      status should equal (200)
      //TODO: Verify was passed into stub
    }
  }
}
