package models

import play.api.PlayException
import reactivemongo.api.MongoDriver
import reactivemongo.api.DB
import reactivemongo.core.nodeset.Authenticate
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Play.current

object MongoContext {
//  val dbName = current.configuration.getString("mongodb.default.db")
//    .getOrElse(throw new PlayException("Configuration error", "Could not find mongodb.default.db in settings"))
//  val user = current.configuration.getString("mongodb.default.user")
//    .getOrElse(throw new PlayException("Configuration error", "Could not find mongodb.default.user in settings"))
//  val password = current.configuration.getString("mongodb.default.password")
//    .getOrElse(throw new PlayException("Configuration error", "Could not find mongodb.default.password in settings"))
//  val host = current.configuration.getString("mongodb.default.host")
//    .getOrElse(throw new PlayException("Configuration error", "Could not find mongodb.default.host in settings"))

//  val driver = new MongoDriver
//  val credentials = List(Authenticate(dbName, user, password))
//  val connection = driver.connection(List(host), nbChannelsPerNode = 5, authentications = credentials)
//  def db(): DB = connection.db(dbName)

  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))
  def db(): DB = connection("timetracker")
}
