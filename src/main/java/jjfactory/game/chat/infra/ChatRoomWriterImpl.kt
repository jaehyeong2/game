package jjfactory.game.chat.infra

import jjfactory.game.chat.domain.ChatMessage
import jjfactory.game.chat.domain.ChatRoom
import jjfactory.game.chat.domain.ChatRoomWriter
import org.springframework.stereotype.Component

@Component
class ChatRoomWriterImpl(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatMessageRepository: ChatMessageRepository
) : ChatRoomWriter {
    override fun write(chatRoom: ChatRoom): ChatRoom {
        return chatRoomRepository.save(chatRoom)
    }

    override fun writeMessage(chatMessage: ChatMessage): ChatMessage {
        return chatMessageRepository.save(chatMessage)
    }
}