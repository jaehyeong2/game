package jjfactory.game.chat.domain

interface ChatRoomWriter {
    fun write(chatRoom: ChatRoom): ChatRoom
    fun writeMessage(chatMessage: ChatMessage): ChatMessage
}