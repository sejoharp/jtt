package models

import reactivemongo.api.MongoDriver
import reactivemongo.api.DB
import scala.concurrent.ExecutionContext.Implicits.global

object MongoContext {
  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  def db(): DB = connection("reactivemongo-extensions")
}
