package jjfactory.game.event.service

import jjfactory.game.event.domain.DailyEvent
import jjfactory.game.event.domain.DailyEventRepository
import org.springframework.stereotype.Component

@Component
class EventWriterImpl(
    private val dailyEventRepository: DailyEventRepository
) : EventWriter {
    override fun write(event: DailyEvent): DailyEvent {
        return dailyEventRepository.save(event)
    }
}