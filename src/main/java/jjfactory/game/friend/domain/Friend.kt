package jjfactory.game.achievement.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(
    indexes = [Index(columnList = "requestUserId, receiveUserId", unique = true)]
)
@Entity
class Friend(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val requestUserId: Long,
    val receiveUserId: Long,

    @Enumerated(EnumType.STRING)
    var status: Status = Status.REQUESTED,
    val requestDt: LocalDateTime = LocalDateTime.now(),
    var acceptDt: LocalDateTime? = null
) {
    enum class Status {
        REQUESTED, DONE
    }

    fun accept(){
        if (status == Status.REQUESTED){
            status = Status.DONE
            acceptDt = LocalDateTime.now()
        }
    }
}