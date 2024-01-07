package jjfactory.game.auth.domain

import org.springframework.stereotype.Component

@Component
class GoogleLoginService(

): LoginService {
    override fun login(token: String) {
        println("google login called")
    }

    override fun support(type: LoginType): Boolean {
        return type == LoginType.GOOGLE
    }
}