package com.lala.bambi.squash

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class SquashRankingServletSpec extends ScalatraSpec {
  def is =
    "GET / on SquashRankingServlet" ^
      "should return status 200" ! root200 ^
    "POST /result on SquashReankingServlet" ^
      "should return status 200" ! createSquashResult ^
    end

  addServlet(classOf[SquashRankingServlet], "/*")

  def root200 = get("/") {
    status must_== 200
  }

  def createSquashResult = post("/result", """ {"player1":"lala", "player2": "bambi"} """.getBytes) {
    status must_== 200
  }
}
