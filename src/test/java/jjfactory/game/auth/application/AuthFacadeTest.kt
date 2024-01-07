package jjfactory.game.auth.application

import jjfactory.game.auth.domain.LoginType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthFacadeTest {
    @Autowired
    lateinit var authFacade: AuthFacade

    @Test
    fun oauthCallTest() {
        authFacade.oauthLogin(LoginType.NAVER, "token")
        authFacade.oauthLogin(LoginType.KAKAO, "token")
        authFacade.oauthLogin(LoginType.GOOGLE, "token")
        authFacade.oauthLogin(LoginType.LINE, "token")

    }

}