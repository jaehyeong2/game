package jjfactory.game.auth.application

import jjfactory.game.auth.domain.LoginService
import jjfactory.game.auth.domain.LoginType
import org.springframework.stereotype.Service

@Service
class AuthFacade(
    private val loginServiceList: List<LoginService>
) {

    fun oauthLogin(type: LoginType, token: String){
        val loginService = loginServiceList.first {
            it.support(type)
        }

        loginService.login(token)
    }
}