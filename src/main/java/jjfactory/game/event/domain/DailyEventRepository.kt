package jjfactory.game.event.domain

import org.springframework.data.jpa.repository.JpaRepository

interface DailyEventRepository : JpaRepository<DailyEvent, Long> {
}