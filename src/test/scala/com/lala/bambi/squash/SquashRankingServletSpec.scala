package com.lala.bambi.squash

import org.scalatest.FunSuite
import org.scalatra.test.scalatest.ScalatraSuite

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class SquashRankingServletSpec extends ScalatraSuite with FunSuite {

  addServlet(classOf[SquashRankingServlet], "/*")

  test("get result") {
    get("/results/2", headers = Map("Accept"->"application/json")) {
      status should equal (200)
      body should include ("lala")
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
    }
  }
}
