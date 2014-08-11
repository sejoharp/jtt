package controllers

import models.{Token, TokenDao}
import org.joda.time.{DateTime, Days, Duration}
import play.api.mvc._
import reactivemongo.bson.BSONObjectID
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class UserRequest[A](val userId: Option[BSONObjectID], request: Request[A]) extends WrappedRequest[A](request)

object UserIdAction
  extends ActionBuilder[UserRequest]
  with ActionTransformer[Request, UserRequest] {

  def transform[A](request: Request[A]) = {
    val tokenId = request.getQueryString("token")
    getUserIdFromToken(tokenId.get).map(new UserRequest(_, request))
  }

  def getUserIdFromToken(tokenId: String): Future[Option[BSONObjectID]] = {
    TokenDao.findById(tokenId).map(token => token match {
      case Some(token) if isValidToken(token) => Some(token.userId)
      case _ => None
    })
  }

  def isValidToken(token: Token): Boolean = {
    val tokenAge = new Duration(token.createdAt, DateTime.now())
    tokenAge.isLongerThan(Days.days(1).toStandardDuration)
  }
}

object PermissionCheckAction extends ActionFilter[UserRequest] {
  def filter[A](request: UserRequest[A]) = Future.successful {
    if (request.userId.isEmpty)
      Some(Results.Unauthorized)
    else
      None
  }
}
