import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

class IntervalControllerSpec extends PlaySpecification {
  "IntervalController" should {
    "respond to the index Action" in new WithApplication() {
      val Some(result) = route(FakeRequest(GET, "/intervals"))

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
//      contentAsString(result) must contain("Hello Bob")
    }
  }
}
