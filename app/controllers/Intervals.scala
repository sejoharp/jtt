package controllers

import controllers.Application._
import play.api.mvc.{Action, Controller}

object Intervals extends Controller {
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
}

