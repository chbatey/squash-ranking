package com.lala.bambi.squash

import org.scalatra._
import scalate.ScalateSupport

class SquashRankingServlet extends SquashRankingStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
  
}
