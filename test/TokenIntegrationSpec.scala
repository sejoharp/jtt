import models.TokenDao
import org.specs2.mutable.Specification

class TokenIntegrationSpec extends Specification {
  "TokenDao" should {
    "save a new one" in new WithDbData {


      await(TokenDao.save(TokenTestdata.token))

      await(TokenDao.findById(TokenTestdata.token._id)) must beSome(TokenTestdata.token)
    }
    "find a valid Token" in new WithDbData {
      await(TokenDao.save(TokenTestdata.token))
      await(TokenDao.findValidById(TokenTestdata.token._id)) must beSome(TokenTestdata.token)
    }
  }
}