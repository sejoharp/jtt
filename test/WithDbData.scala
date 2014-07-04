import models.{IntervalDao, UserDao}
import org.specs2.execute.{AsResult, Result}
import play.api.test.WithApplication

abstract class WithDbData extends WithApplication {
  override def around[T: AsResult](t: => T): Result = super.around {
    setupData()
    t
  }

  def setupData() {
    UserDao.removeAll()
    IntervalDao.removeAll()
  }
}