import models.User

object UserTestdata {
  val user1: User = User(name = "testuser", workTimePerWeek = 3660)
  val user2: User = User(name = "testuser2", workTimePerWeek = 3666)
}
