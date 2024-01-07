package jjfactory.game.auth.domain

import org.springframework.stereotype.Component

@Component
class NaverLoginService: LoginService {
    override fun login(token: String) {
        println("naver login called")
    }

    override fun support(type: LoginType): Boolean {
        return type == LoginType.NAVER
    }
}