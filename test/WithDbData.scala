import models.{IntervalDao, UserDao}
import org.specs2.execute.{AsResult, Result}
import play.api.test.WithApplication

import scala.concurrent.{Awaitable, Await}
import scala.concurrent.duration.Duration

abstract class WithDbData extends WithApplication {
  override def around[T: AsResult](t: => T): Result = super.around {
    setupData()
    t
  }

  def setupData() {
    await(UserDao.removeAll())
    await(IntervalDao.removeAll())
  }

  def await[T](awaitable: Awaitable[T], atMost: Duration = Duration(5, "sec")): T = Await.result(awaitable,atMost)
}