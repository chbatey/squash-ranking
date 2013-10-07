import com.lala.bambi.squash._
import com.lala.bambi.squash.web.SquashRankingServlet
import grizzled.slf4j.Logging
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle with Logging {
  override def init(context: ServletContext) {
    info("Env vars: " + System.getenv())
    implicit val bindingModule = ProjectConfiguration
    context.mount(new SquashRankingServlet, "/*")
  }
}
