package jjfactory.game.chat.infra

import jjfactory.game.chat.domain.ChatRoom
import jjfactory.game.chat.domain.ChatRoomReader
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class ChatRoomReaderImpl(
    private val chatRoomRepository: ChatRoomRepository
) : ChatRoomReader {
    override fun findById(id: Long): ChatRoom {
        return chatRoomRepository.findByIdOrNull(id) ?: throw RuntimeException()
    }

    override fun findByInviterId(inviterId: Long, pageable: Pageable): Page<ChatRoom?> {
        return chatRoomRepository.findPage(pageable) {
            select(
                entity(ChatRoom::class)
            ).from(
                entity(ChatRoom::class)
            ).where(
                path(ChatRoom::inviterId).eq(inviterId)
            )
        }
    }

    override fun findByInviterIdAndInviteeId(inviterId: Long, inviteeId: Long): ChatRoom {
        return chatRoomRepository.findByInviteeIdAndInviteeId(inviterId, inviteeId)
    }
}