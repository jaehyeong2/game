package jjfactory.game.stage.service

import jjfactory.game.stage.domain.Chapter
import jjfactory.game.stage.domain.Stage
import jjfactory.game.stage.domain.StageRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class StageReaderImpl(
    private val stageRepository: StageRepository
) : StageReader {
    override fun findChapterById(id: Long): Chapter {
        TODO("Not yet implemented")
    }

    override fun findStageById(id: Long): Stage {
        return stageRepository.findByIdOrNull(id) ?: throw NotFoundException()
    }
}