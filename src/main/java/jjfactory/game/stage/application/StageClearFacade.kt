package jjfactory.game.stage.application

import jjfactory.game.event.UserStageClearedEvent
import jjfactory.game.stage.domain.clear.StageClearCommand
import jjfactory.game.stage.service.StageReader
import jjfactory.game.stage.service.clear.StageClearWriter
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StageClearFacade(
    private val stageClearWriter: StageClearWriter,
    private val stageReader: StageReader,
    private val redisTemplate: RedisTemplate<String, String>,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    val rankKey = "daily-active-rank"


    @Transactional
    fun clear(stageId: Long, command: StageClearCommand.Create, userId: Long): Long {
        val stage = stageReader.findStageById(stageId)
        applicationEventPublisher.publishEvent(UserStageClearedEvent(userId, stage.exp, stage.ordering))

        val initStageClear = command.toEntity(stageId, userId)
        //일간 랭킹
        redisTemplate.opsForZSet().incrementScore(rankKey, userId.toString(), stage.clearPoint.toDouble())

        //todo 저장에 실패하면?
        val stageClear = stageClearWriter.write(initStageClear)

        return stageClear.id!!
    }
}