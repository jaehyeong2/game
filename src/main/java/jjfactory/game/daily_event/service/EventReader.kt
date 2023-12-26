package jjfactory.game.daily_event.service

import jjfactory.game.daily_event.domain.DailyEvent
import jjfactory.game.daily_event.domain.log.DailyEventLog

interface EventReader {
    fun findById(id: Long): DailyEvent
    fun findLogByEventIdAndUserId(eventId: Long, userId: Long): DailyEventLog
    fun existLogByEventIdAndUserId(eventId: Long, userId: Long): Boolean
}