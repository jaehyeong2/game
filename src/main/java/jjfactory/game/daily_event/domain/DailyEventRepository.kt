package jjfactory.game.daily_event.domain

import org.springframework.data.jpa.repository.JpaRepository

interface DailyEventRepository : JpaRepository<DailyEvent, Long> {

}