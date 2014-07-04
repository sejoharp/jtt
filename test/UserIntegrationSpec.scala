import models.{User, UserDao}
import org.specs2.mutable.Specification

import scala.util.Success

class UserIntegrationSpec extends Specification {
  "User" should {
    "save one instance." in new WithDbData {
      UserDao.save(UserTestdata.user1)

      UserDao.findAll()
        .onComplete({ case Success(all) =>
        all.head must equalTo(UserTestdata.user1)
        all.size must equalTo(1)
      })
    }
    "get all instances." in new WithDbData {
      UserDao.save(UserTestdata.user1)
      UserDao.save(UserTestdata.user2)

      UserDao.findAll()
        .onComplete({ case Success(all) =>
        all.head must equalTo(UserTestdata.user1)
        all.tail.head must equalTo(UserTestdata.user2)
        all.size must equalTo(2)
      })
    }
    "remove one instance." in new WithDbData {
      UserDao.save(UserTestdata.user1)

      UserDao.removeById(UserTestdata.user1._id)

      UserDao.count()
        .onComplete({ case Success(amount) => amount must equalTo(0)})
    }
    "count all instances." in new WithDbData {
      UserDao.save(UserTestdata.user1)
      UserDao.save(UserTestdata.user2)

      UserDao.count() must equalTo(2)
    }
    "change worktime per week" in new WithDbData {
      UserDao.save(UserTestdata.user1)
      val newUser1 = User(UserTestdata.user1._id, UserTestdata.user1.name, 1000)

      UserDao.save(newUser1)

      UserDao.findById(newUser1._id)
        .onComplete({ case Success(user) => user must equalTo(newUser1)})
    }
  }
}
