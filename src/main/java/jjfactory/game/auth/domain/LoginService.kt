package jjfactory.game.auth.domain

interface LoginService {
    fun support(type: LoginType): Boolean
    fun login(token: String)
}