package jjfactory.game.event.service

import jjfactory.game.event.domain.DailyEvent
import jjfactory.game.event.domain.DailyEventRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class EventReaderImpl(
    private val eventRepository: DailyEventRepository
) : EventReader {
    override fun findById(id: Long): DailyEvent {
        return eventRepository.findByIdOrNull(id) ?: throw RuntimeException()
    }
}