package models

import reactivemongo.bson._
import reactivemongo.extensions.dao.BsonDao

case class User(_id: BSONObjectID = BSONObjectID.generate,
                name: String,
                workTimePerWeek: Long)

object User {
  implicit val userHandler = Macros.handler[User]
}

object UserDao extends BsonDao[User, BSONObjectID](MongoContext.db, "users")