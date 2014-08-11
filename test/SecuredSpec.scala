import org.specs2.mutable.Specification
import play.api.mvc.Controller
import play.api.test.FakeRequest
import play.api.test._
import play.api.test.Helpers._

class SecuredSpec extends Specification {
//
//  "Secured trait" should {
//    "dectect valid user" in {
//      val result = SecuredActionController.index()(FakeRequest().withHeaders(("token", "value")))
//      status(result) must equalTo(OK)
//    }
//    "dectect invalid user" in {
//      val result = SecuredActionController.index()(FakeRequest())
//      status(result) must equalTo(UNAUTHORIZED)
//    }
//  }
}

object SecuredActionController extends Controller {
//  def index() = AuthenticatedAction {
//    userid => Ok
//  }
}