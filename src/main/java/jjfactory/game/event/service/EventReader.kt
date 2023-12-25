package jjfactory.game.event.service

import jjfactory.game.event.domain.DailyEvent

interface EventReader {
    fun findById(id: Long): DailyEvent
}