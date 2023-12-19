package jjfactory.game.stage.service

import jjfactory.game.stage.domain.Chapter

interface StageReader {
    fun findChapterById(id: Long): Chapter
}