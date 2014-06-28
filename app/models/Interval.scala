package models

import reactivemongo.bson._
import reactivemongo.extensions.dao.Handlers._
import org.joda.time.DateTime
import reactivemongo.extensions.dao.BsonDao
import scala.concurrent.Future

case class Interval(_id: BSONObjectID = BSONObjectID.generate,
  userId: BSONObjectID,
  start: DateTime,
  stop: DateTime = null)

object Interval {
  implicit val personHandler = Macros.handler[Interval]
}

object IntervalDao extends BsonDao[Interval, BSONObjectID](MongoContext.db, "intervals") {
  def findAllInRange(userid: BSONObjectID, start: DateTime, stop: DateTime): Future[List[Interval]] = {
    findAll(BSONDocument("$and" -> BSONArray(
      BSONDocument("userId" -> userid),
      BSONDocument("start" -> BSONDocument("$gte" -> start, "$lte" -> stop)))))
  }

  def isUserWorking(userId: BSONObjectID): Future[Boolean] = {
    count(BSONDocument("$and" -> BSONArray(
      BSONDocument("userId" -> userId),
      BSONDocument("stop" -> null)))).map(amount => amount > 0)
  }
}