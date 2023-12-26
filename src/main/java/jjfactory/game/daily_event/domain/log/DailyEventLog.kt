package jjfactory.game.daily_event.domain.log

import jakarta.persistence.*
import jjfactory.game.daily_event.domain.DailyEvent
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime


@Table(
    indexes = [Index(columnList = "userId, eventId", unique = true)]
)
@Entity
class DailyEventLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val event: DailyEvent,
    val userId: Long,
    val rewardMoney: Int,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {

}