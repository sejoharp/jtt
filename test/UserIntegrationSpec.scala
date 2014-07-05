import models.{User, UserDao}
import org.specs2.mutable.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

class UserIntegrationSpec extends Specification {
  "User" should {
    "save one instance." in new WithDbData {
      await(UserDao.save(UserTestdata.user1))

      val intervals = await(UserDao.findAll())
          intervals.head must equalTo(UserTestdata.user1)
          intervals.size must equalTo(1)
    }
    "get all instances." in new WithDbData {
      await(UserDao.save(UserTestdata.user1))
      await(UserDao.save(UserTestdata.user2))

      val intervals = await(UserDao.findAll())
          intervals.head must equalTo(UserTestdata.user1)
          intervals.tail.head must equalTo(UserTestdata.user2)
          intervals.size mustEqual 2
    }
    "remove one instance." in new WithDbData {
      await(UserDao.save(UserTestdata.user1))

      await(UserDao.removeById(UserTestdata.user1._id))

      await(UserDao.count()) mustEqual 0
    }
    "count all instances." in new WithDbData {
      await(UserDao.save(UserTestdata.user1))
      await(UserDao.save(UserTestdata.user2))

      await(UserDao.count()) mustEqual 2
    }
    "change worktime per week" in new WithDbData {
      await(UserDao.save(UserTestdata.user1))
      val newUser1 = User(UserTestdata.user1._id, UserTestdata.user1.name, 1000)

      UserDao.save(newUser1)

      await(UserDao.findById(newUser1._id)) must beSome(newUser1)
    }
  }
}
