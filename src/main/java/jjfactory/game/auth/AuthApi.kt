package jjfactory.game.auth

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthApi(
    private val authRetrofit: AuthRetrofit
) {

    @GetMapping("/kakao")
    fun getCode(
        @RequestParam(required = false) code: String?,
    ) {

        println("code = ${code}")

        if (code != null){
            val result = authRetrofit.postCode(
                grantType = "authorization_code",
                clientId = "dummy",
                code = code,
                redirectUri = "http://localhost:3000"
            ).execute()

            println("result = ${result}")
        }
    }
}


data class PostCodeRequest(
    val grantType: String = "authorization_code",
    val clientId: String,
    val redirectUri: String,
    val code: String
)