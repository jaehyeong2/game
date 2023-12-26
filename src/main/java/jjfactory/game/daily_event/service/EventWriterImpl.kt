package jjfactory.game.daily_event.service

import jjfactory.game.daily_event.domain.DailyEvent
import jjfactory.game.daily_event.domain.DailyEventRepository
import jjfactory.game.daily_event.domain.log.DailyEventLog
import jjfactory.game.daily_event.domain.log.DailyEventLogRepository
import org.springframework.stereotype.Component

@Component
class EventWriterImpl(
    private val dailyEventRepository: DailyEventRepository,
    private val dailyEventLogRepository: DailyEventLogRepository
) : EventWriter {
    override fun write(event: DailyEvent): DailyEvent {
        return dailyEventRepository.save(event)
    }

    override fun writeLog(log: DailyEventLog): DailyEventLog {
        return dailyEventLogRepository.save(log)
    }


}