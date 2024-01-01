package jjfactory.game.chat.infra

import jjfactory.game.chat.domain.ChatRoom
import jjfactory.game.chat.domain.ChatRoomWriter
import org.springframework.stereotype.Component

@Component
class ChatRoomWriterImpl(
    private val chatRoomRepository: ChatRoomRepository
) : ChatRoomWriter {
    override fun write(chatRoom: ChatRoom): ChatRoom {
        return chatRoomRepository.save(chatRoom)
    }
}