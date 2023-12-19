package jjfactory.game.stage.service

import jjfactory.game.stage.domain.Chapter
import org.springframework.stereotype.Component

@Component
class StageReaderImpl : StageReader {
    override fun findChapterById(id: Long): Chapter {
        TODO("Not yet implemented")
    }
}