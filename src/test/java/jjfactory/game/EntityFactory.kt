package jjfactory.game

import jjfactory.game.daily_event.domain.DailyEvent
import jjfactory.game.stage.domain.Chapter
import jjfactory.game.stage.domain.Stage
import jjfactory.game.user.domain.User
import org.springframework.boot.test.context.TestComponent

@TestComponent
class EntityFactory {

    fun createNoPointUser(): User {
        return User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100
        )
    }

    fun createNoPointUser(money: Long): User {
        return User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100,
            money = money
        )
    }

    fun createChapter(): Chapter {
        return Chapter(
            ordering = 1,
            name = "chapter1",
            description = "챕터1입니다."
        )
    }

    fun createStage(chapter: Chapter): Stage {
        return Stage(
            chapter = chapter,
            clearPoint = 300,
            ordering = 1,
            name = "stage 1"
        )
    }

    fun createEvent(): DailyEvent {
        return DailyEvent(
            name = "5000원 주는 이벤트!",
            stock = 100,
            rewardMoney = 5000
        )
    }

    fun createEvent(stock: Int): DailyEvent {
        return DailyEvent(
            name = "5000원 주는 이벤트!",
            stock = stock,
            rewardMoney = 5000
        )
    }
}