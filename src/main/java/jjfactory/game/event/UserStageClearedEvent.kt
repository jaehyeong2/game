package jjfactory.game.event

data class UserStageClearedEvent(
  val userId: Long,
  val exp: Int,
  val stageOrdering: Int
)