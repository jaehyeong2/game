package jjfactory.game.stage.application

import jjfactory.game.stage.domain.ChapterCommand
import jjfactory.game.stage.domain.StageCommand
import jjfactory.game.stage.service.StageFactory
import jjfactory.game.stage.service.StageReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StageFacade(
    private val stageFactory: StageFactory,
    private val stageReader: StageReader
) {

    @Transactional
    fun writeChapter(command: ChapterCommand.Create): Long {
        val chapter = stageFactory.writeChapter(command.toEntity())
        return chapter.id!!
    }

    @Transactional
    fun writeStage(command: List<StageCommand.Create>, chapterId: Long) {
        val chapter = stageReader.findChapterById(chapterId)

        command.forEach {
            val initStage = it.toEntity(chapter)
            stageFactory.writeStage(initStage)
        }
    }
}