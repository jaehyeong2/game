package jjfactory.game.user.service

import jjfactory.game.user.domain.User

interface UserReader {
    fun findById(id: Long): User
    fun existById(id: Long): Boolean
}