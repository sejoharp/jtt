import models.Token
import play.api.libs.Crypto
import reactivemongo.bson.BSONObjectID

object TokenTestdata {
  val token = Token(Crypto.generateSignedToken, BSONObjectID.generate)
}
