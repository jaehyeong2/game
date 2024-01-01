package jjfactory.game.chat.infra

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import jjfactory.game.chat.domain.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<ChatRoom, Long>, KotlinJdslJpqlExecutor {
    fun findByInviteeIdAndInviteeId(inviterId: Long, inviteeId: Long): ChatRoom
}