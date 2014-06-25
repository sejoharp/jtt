package models

import reactivemongo.bson._
import reactivemongo.extensions.dao.Handlers._
import org.joda.time.DateTime
import reactivemongo.extensions.dao.BsonDao

case class User(id: BSONObjectID = BSONObjectID.generate,
  name: String,
  workTimePerWeek: Long)

object User {
  implicit val personHandler = Macros.handler[User]
}

object UserDao extends BsonDao[Interval, BSONObjectID](MongoContext.db, "users")