import models.Interval
import org.joda.time.{Days, Hours, DateTime}

object IntervalTestdata {
  val interval1 = Interval(userId = UserTestdata.user1._id, start = new DateTime(), stop = Some(DateTime.now().plus(Days.ONE)))
  val interval2 = Interval(userId = UserTestdata.user1._id, start = new DateTime(), stop = Some(DateTime.now().plus(Hours.ONE)))
  val interval3 = Interval(userId = UserTestdata.user1._id, start = new DateTime(2014,4,10,3,0), stop = Some(new DateTime(2014,4,10,3,0).plus(Hours.ONE)))
  val interval4 = Interval(userId = UserTestdata.user1._id, start = new DateTime(2014,4,11,4,0), stop = Some(new DateTime(2014,4,11,4,0).plus(Hours.FIVE)))
  val intervalOpen = Interval(userId = UserTestdata.user1._id, start = new DateTime(2014,4,11,4,0))
}
