package jjfactory.game.daily_event.service

import jjfactory.game.daily_event.domain.DailyEvent
import jjfactory.game.daily_event.domain.log.DailyEventLog

interface EventWriter {
    fun write(event: DailyEvent): DailyEvent
    fun writeLog(log: DailyEventLog): DailyEventLog
}