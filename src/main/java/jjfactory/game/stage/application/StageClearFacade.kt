package jjfactory.game.stage.application

import jjfactory.game.stage.domain.clear.StageClearCommand
import jjfactory.game.stage.service.StageReader
import jjfactory.game.stage.service.clear.StageClearWriter
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StageClearFacade(
    private val stageClearWriter: StageClearWriter,
    private val stageReader: StageReader,
    private val redisTemplate: RedisTemplate<String, Long>
) {

    @Transactional
    fun clear(stageId: Long, command: StageClearCommand.Create, userId: Long): Long {
        val initStageClear = command.toEntity(stageId, userId)

        val stage = stageReader.findStageById(stageId)

        //일간 랭킹
        val rankKey = "daily-active-rank"
        redisTemplate.opsForZSet().incrementScore(rankKey, userId, stage.clearPoint.toDouble())


        //todo user level up 기능 필요
        //todo user 접근 스테이지 갱신 필요
        val stageClear = stageClearWriter.write(initStageClear)
        return stageClear.id!!
    }
}