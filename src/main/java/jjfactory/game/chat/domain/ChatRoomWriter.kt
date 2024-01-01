package jjfactory.game.chat.domain

interface ChatRoomWriter {
    fun write(chatRoom: ChatRoom): ChatRoom
}