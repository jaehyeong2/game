package jjfactory.game.chat.infra

import jjfactory.game.chat.domain.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository: JpaRepository<ChatRoom, Long> {
    fun findAllByInviterId(inviterId: Long)
}