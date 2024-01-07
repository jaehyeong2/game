package jjfactory.game.auth.domain

import org.springframework.stereotype.Component

@Component
class LineLoginService(

): LoginService {
    override fun login(token: String) {
        println("line login called")
    }

    override fun support(type: LoginType): Boolean {
        return type == LoginType.LINE
    }
}