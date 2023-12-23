package jjfactory.game

import jjfactory.game.user.domain.User

class EntityFactory {

    fun createUser(): User {
        return User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100
        )
    }
}