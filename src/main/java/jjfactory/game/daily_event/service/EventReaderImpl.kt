package jjfactory.game.daily_event.service

import jjfactory.game.daily_event.domain.DailyEvent
import jjfactory.game.daily_event.domain.DailyEventRepository
import jjfactory.game.daily_event.domain.log.DailyEventLog
import jjfactory.game.daily_event.domain.log.DailyEventLogRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class EventReaderImpl(
    private val eventRepository: DailyEventRepository,
    private val dailyEventLogRepository: DailyEventLogRepository
) : EventReader {
    override fun findById(id: Long): DailyEvent {
        return eventRepository.findByIdOrNull(id) ?: throw RuntimeException()
    }

    override fun findLogByEventIdAndUserId(eventId: Long, userId: Long): DailyEventLog {
        return dailyEventLogRepository.findByEventIdAndUserId(eventId, userId)
    }

    override fun existLogByEventIdAndUserId(eventId: Long, userId: Long): Boolean {
        return dailyEventLogRepository.existsByEventIdAndUserId(eventId, userId)
    }
}