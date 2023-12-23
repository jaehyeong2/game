package jjfactory.game.guild.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Guild(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    var name: String,
    val createUserId: Long,
    var level: Int = 1,
    val userCnt: Int = 1,

    val createAt: LocalDateTime = LocalDateTime.now()
) {

    fun levelUp() {
        level++
    }

}