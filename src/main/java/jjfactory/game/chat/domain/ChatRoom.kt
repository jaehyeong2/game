package jjfactory.game.chat.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Table(
    indexes = [Index(columnList = "inviterId, inviteeId", unique = true)]
)
@Entity
class ChatRoom(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val inviterId: Long,
    val inviteeId: Long,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null
){
}