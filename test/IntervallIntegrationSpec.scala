import models.IntervalDao
import org.joda.time.DateTime
import org.specs2.mutable.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Success

class IntervalIntegrationSpec extends Specification {
  "Interval" should {
    "save one instance." in new WithDbData {
      IntervalDao.save(IntervalTestdata.interval1)

      IntervalDao.findAll()
        .onComplete({
        case Success(all) =>
          all.head must equalTo(IntervalTestdata.interval1)
          all.size must equalTo(1)
      })
    }
    "get all from 10.4.2014." in new WithDbData {
      IntervalDao.save(IntervalTestdata.interval1)
      IntervalDao.save(IntervalTestdata.interval2)
      IntervalDao.save(IntervalTestdata.interval3)
      IntervalDao.save(IntervalTestdata.interval4)

      IntervalDao.findAllInRange(userid = UserTestdata.user1._id,
        start = new DateTime(2014, 4, 10, 0, 0),
        stop = new DateTime(2014, 4, 10, 23, 59))
        .onSuccess({
        case intervals =>
          intervals.size must equalTo(1)
          intervals.head must equalTo(IntervalTestdata.interval3)
      })
    }
    "detect not working user." in new WithDbData {
      IntervalDao.save(IntervalTestdata.interval1)
      IntervalDao.save(IntervalTestdata.interval2)

      Await.result(IntervalDao.isUserWorking(IntervalTestdata.interval1.userId), Duration(2, "sec")) must beFalse
    }
    "detect working user." in new WithDbData {
      IntervalDao.save(IntervalTestdata.interval3)
      IntervalDao.save(IntervalTestdata.intervalOpen)

      IntervalDao.isUserWorking(IntervalTestdata.interval1.userId)
        .onComplete({
        case Success(isWorking) => isWorking must beTrue
      })
    }
  }
}