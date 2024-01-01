package jjfactory.game.chat.application

import jjfactory.game.chat.domain.*
import jjfactory.game.user.service.UserReader
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatRdbService(
    private val chatRoomWriter: ChatRoomWriter,
    private val chatRoomReader: ChatRoomReader,
    private val userReader: UserReader
): ChatService {

    @Transactional
    override fun createChatRoom(loginUserId: Long, inviteeId: Long): ChatRoom {
        if(userReader.existById(inviteeId)) throw RuntimeException()

        val room = ChatRoom(
            inviterId = loginUserId,
            inviteeId = inviteeId
        )

        return chatRoomWriter.write(room)
    }

    override fun findMyChatRoomPages(inviterId: Long, pageable: Pageable) {
        chatRoomReader.findByInviterId(inviterId, pageable)
    }

    override fun sendMessage(roomId: Long, command: ChatCommand.CreateMessage, loginUserId: Long): Long {
        val message = command.toEntity(roomId, loginUserId)
        return chatRoomWriter.writeMessage(message).id!!
    }

}