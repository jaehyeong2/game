package jjfactory.game.event.application

import jjfactory.game.event.domain.DailyEventCommand
import jjfactory.game.event.exception.AlreadyJoinedException
import jjfactory.game.event.exception.NotOpenedException
import jjfactory.game.event.service.EventReader
import jjfactory.game.event.service.EventWriter
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
        if (!event.isOn) throw NotOpenedException()

        if(redisTemplate.opsForSet().isMember(key, userId.toString())) throw  AlreadyJoinedException()
        if (redisTemplate.opsForSet().size(key) >= event.stock) throw RuntimeException()

        redisTemplate.opsForSet().add(key, userId.toString())

        //todo rdb에 저장.
        //todo redis -failover전략
        //todo rdb에 저장하는건 비동기로?
    }
}