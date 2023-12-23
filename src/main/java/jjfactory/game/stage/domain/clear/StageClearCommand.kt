package jjfactory.game.stage.domain.clear

import jjfactory.game.user.domain.stage.StageClear
import java.time.LocalDateTime

class StageClearCommand {
    data class Create(
        val playTime: Int
    ){
        fun toEntity(stageId: Long, userId: Long): StageClear{
            return StageClear(
                userId = userId,
                playTime = playTime,
                stageId = stageId
            )
        }
    }
}