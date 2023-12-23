package jjfactory.game.stage.service.clear

import jjfactory.game.user.domain.stage.StageClear

interface StageClearWriter {
    fun write(stageClear: StageClear): StageClear
}