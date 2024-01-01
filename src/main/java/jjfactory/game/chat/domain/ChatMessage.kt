package jjfactory.game.chat.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Table(
    indexes = [Index(columnList = "userId, roomId")]
)
@Entity
class ChatMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val roomId: Long,
    val userId: Long,

    val content: String,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null

) {
}