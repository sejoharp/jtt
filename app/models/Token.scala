package models

import models.IntervalDao._
import org.joda.time.DateTime
import reactivemongo.bson._
import reactivemongo.extensions.dao.Handlers._
import reactivemongo.api.indexes.{Index, IndexType}
import reactivemongo.bson._
import reactivemongo.extensions.dao.BsonDao

import scala.concurrent.Future
import scala.util.Success

case class Token(_id: String,
                 userId: BSONObjectID,
                 createdAt: DateTime = DateTime.now())

object Token {
  implicit val userHandler = Macros.handler[Token]
}

object TokenDao extends BsonDao[Token, String](MongoContext.db, "tokens"){
  def findValidById(tokenId: String): Future[Option[BSONObjectID]] = {
    findOne(BSONDocument("$and" -> BSONArray(
      BSONDocument("_id" -> tokenId),
      BSONDocument("createdAt" -> BSONDocument("$lt" -> DateTime.now()))))).map({
      case Success(token) =>  
    })
  }

}