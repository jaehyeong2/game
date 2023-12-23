package jjfactory.game.user.domain.stage

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Table(
    indexes = [Index(columnList = "stageId, userId", unique = true)]
)
@Entity
class StageClear(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: Long,
    val stageId: Long,

    // 클리어 시간
    val clearTime: LocalDateTime = LocalDateTime.now(),
    @Comment("ms 단위")
    val playTime: Int
) {
}