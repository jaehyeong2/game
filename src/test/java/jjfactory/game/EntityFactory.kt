package jjfactory.game

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
}