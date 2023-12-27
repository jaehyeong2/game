package jjfactory.game.daily_event.application

import jjfactory.game.daily_event.domain.DailyEvent
import jjfactory.game.daily_event.domain.DailyEventCommand
import jjfactory.game.daily_event.domain.log.DailyEventLog
import jjfactory.game.event.DailyEventJoinedEvent
import jjfactory.game.daily_event.exception.AlreadyJoinedException
import jjfactory.game.daily_event.exception.NotOpenedException
import jjfactory.game.daily_event.service.EventReader
import jjfactory.game.daily_event.service.EventWriter
import jjfactory.game.user.service.UserReader
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventDbService(
    private val eventReader: EventReader,
    private val eventWriter: EventWriter,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
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

        event.decreaseStock()
        writeLog(event, userId)

        applicationEventPublisher.publishEvent(DailyEventJoinedEvent(userId, event.rewardMoney))
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
        validateDuplicate(event.id!!, userId)
    }

    private fun validateOpen(event: DailyEvent) {
        if (!event.isOn) throw NotOpenedException()
    }

    private fun validateDuplicate(eventId: Long, userId: Long) {
        if (eventReader.existLogByEventIdAndUserId(eventId, userId)) throw AlreadyJoinedException()
    }
}