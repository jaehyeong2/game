package jjfactory.game

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
}