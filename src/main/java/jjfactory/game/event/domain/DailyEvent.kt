package jjfactory.game.event.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class DailyEvent(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,

    var stock: Int,
    var rewardMoney: Int,
    var isOn: Boolean = false,
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var startedAt: LocalDateTime? = null
) {

    fun open(){
        if(!isOn){
            isOn = true
            startedAt = LocalDateTime.now()
        }
    }
}