package jjfactory.game.user.domain.support

import jjfactory.game.event.DailyEventJoinedEvent
import jjfactory.game.event.UserStageClearedEvent
import jjfactory.game.user.service.UserReader
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserEventListener(
    private val userReader: UserReader
) {

//    @Async
    @EventListener
    fun dailyEventJoinedEventListener(event: DailyEventJoinedEvent){
        val user = userReader.findById(event.userId)
        user.increaseMoney(event.rewardMoney)
    }

    @EventListener
    fun stageClearEventListener(event: UserStageClearedEvent){
        val user = userReader.findById(event.userId)

        user.increaseStageLevel(event.stageOrdering)
        user.increaseExp(event.exp)
    }
}