package jjfactory.game.chat.application

import jjfactory.game.chat.domain.ChatCommand
import jjfactory.game.chat.domain.ChatRoom
import org.springframework.data.domain.Pageable

interface ChatService {
    fun createChatRoom(loginUserId: Long, inviteeId: Long): ChatRoom
    fun findMyChatRoomPages(inviterId: Long, pageable: Pageable)
    fun sendMessage(roomId: Long, command: ChatCommand.CreateMessage, loginUserId: Long): Long
}