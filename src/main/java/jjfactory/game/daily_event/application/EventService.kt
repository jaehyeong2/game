package jjfactory.game.daily_event.application

import jjfactory.game.daily_event.domain.DailyEvent
import jjfactory.game.daily_event.domain.DailyEventCommand
import jjfactory.game.daily_event.domain.log.DailyEventLog
import jjfactory.game.daily_event.exception.AlreadyJoinedException
import jjfactory.game.daily_event.exception.NotOpenedException
import jjfactory.game.daily_event.exception.SoldOutException
import jjfactory.game.daily_event.service.EventReader
import jjfactory.game.daily_event.service.EventWriter
import jjfactory.game.user.service.UserReader
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventService(
    private val userReader: UserReader,
    private val eventReader: EventReader,
    private val eventWriter: EventWriter,
    private val redisTemplate: RedisTemplate<String, String>
) {

    var key = "event-stock"

    @Transactional
    fun createEvent(command: DailyEventCommand.Create): Long {
        val initEvent = command.toEntity()
        val event = eventWriter.write(initEvent)

        return event.id!!
    }

    @Transactional
    fun openEvent(id: Long){
        val event = eventReader.findById(id)
        event.open()
    }

    @Transactional
    fun joinEvent(eventId:Long, userId: Long){
        val event = eventReader.findById(eventId)
        validate(event, userId)

        redisTemplate.opsForSet().add(key, userId.toString())
        //레디스가 뻗었으면?

        writeLog(event, userId)

        //todo redis -failover전략
        //todo rdb에 저장하는건 비동기로?
    }

    private fun writeLog(event: DailyEvent, userId: Long) {
        val initLog = DailyEventLog(
            event = event,
            rewardMoney = event.rewardMoney,
            userId = userId
        )

        eventWriter.writeLog(initLog)
    }

    private fun validate(event: DailyEvent, userId: Long){
        validateOpen(event)
        validateDuplicate(userId)
        validateQuantity(event)
    }

    private fun validateOpen(event: DailyEvent) {
        if (!event.isOn) throw NotOpenedException()
    }

    private fun validateDuplicate(userId: Long) {
        if (redisTemplate.opsForSet().isMember(key, userId.toString())) throw AlreadyJoinedException()
    }

    private fun validateQuantity(event: DailyEvent) {
        if (redisTemplate.opsForSet().size(key) >= event.stock) throw SoldOutException()
    }
}