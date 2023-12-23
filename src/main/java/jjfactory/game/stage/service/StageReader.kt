package jjfactory.game.stage.service

import jjfactory.game.stage.domain.Chapter
import jjfactory.game.stage.domain.Stage

interface StageReader {
    fun findChapterById(id: Long): Chapter
    fun findStageById(id: Long): Stage
}