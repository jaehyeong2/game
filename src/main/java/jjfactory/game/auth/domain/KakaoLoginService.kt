package jjfactory.game.auth.domain

import org.springframework.stereotype.Component

@Component
class KakaoLoginService(

): LoginService {
    override fun login(token: String) {
        println("kakao login called")
    }

    override fun support(type: LoginType): Boolean {
        return type == LoginType.KAKAO
    }
}