package jjfactory.game.event.service

import jjfactory.game.event.domain.DailyEvent

interface EventWriter {
    fun write(event: DailyEvent): DailyEvent
}