package jjfactory.game.chat.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ChatRoomReader {
    fun findById(id: Long): ChatRoom
    fun findByInviterIdAndInviteeId(inviterId: Long, inviteeId: Long): ChatRoom
    fun findByInviterId(inviterId: Long, pageable: Pageable): Page<ChatRoom?>
}