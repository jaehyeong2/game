package jjfactory.game.stage.service

import jjfactory.game.stage.domain.Chapter
import jjfactory.game.stage.domain.Stage

interface StageFactory {
    fun writeStage(stage: Stage): Stage
    fun writeChapter(chapter: Chapter): Chapter
}