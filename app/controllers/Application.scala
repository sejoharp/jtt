package controllers

import play.api.mvc._

object Application extends MyService {


}

trait MyService extends Controller {
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
}

