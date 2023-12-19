package jjfactory.game.stage.presentation

import jjfactory.game.stage.application.StageFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chapters")
class StageApi(
    private val stageFacade: StageFacade
) {

    @PostMapping
    fun postChapter(){

    }
}