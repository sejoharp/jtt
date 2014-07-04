import models.IntervalDao
import org.joda.time.DateTime
import org.specs2.mutable.Specification

class IntervalIntegrationSpec extends Specification {
  "Interval" should {
    "save one instance." in new WithDbData {
      IntervalDao.save(IntervalTestdata.interval1)

      await(IntervalDao.findAll()) mustEqual List(IntervalTestdata.interval1)
    }
    "get all from 10.4.2014." in new WithDbData {
      IntervalDao.save(IntervalTestdata.interval1)
      IntervalDao.save(IntervalTestdata.interval2)
      IntervalDao.save(IntervalTestdata.interval3)
      IntervalDao.save(IntervalTestdata.interval4)

      val intervals = await(IntervalDao.findAllInRange(
        userid = UserTestdata.user1._id,
        start = new DateTime(2014, 4, 10, 0, 0),
        stop = new DateTime(2014, 4, 10, 23, 59)))

      intervals.size must equalTo(1)
      intervals.head must equalTo(IntervalTestdata.interval3)
    }
    "detect not working user." in new WithDbData {
      IntervalDao.save(IntervalTestdata.interval1)
      IntervalDao.save(IntervalTestdata.interval2)

      await(IntervalDao.isUserWorking(IntervalTestdata.interval1.userId)) must beFalse
    }
    "detect working user." in new WithDbData {
      IntervalDao.save(IntervalTestdata.interval3)
      IntervalDao.save(IntervalTestdata.intervalOpen)

      await(IntervalDao.isUserWorking(IntervalTestdata.interval1.userId)) must beTrue
    }
  }
}