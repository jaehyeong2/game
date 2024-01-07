package jjfactory.game.auth.presentation

import jjfactory.game.auth.application.AuthFacade
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authFacade: AuthFacade
) {
}