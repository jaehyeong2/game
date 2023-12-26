package jjfactory.game.daily_event.domain.log

import org.springframework.data.jpa.repository.JpaRepository

interface DailyEventLogRepository : JpaRepository<DailyEventLog, Long> {
    fun findByEventIdAndUserId(eventId: Long, userId: Long): DailyEventLog
    fun existsByEventIdAndUserId(eventId: Long, userId: Long): Boolean
}