package jjfactory.game.chat.domain

class ChatCommand {
    data class CreateMessage(
        val content: String
    ) {
        fun toEntity(roomId: Long, userId: Long): ChatMessage {
            return ChatMessage(
                roomId = roomId,
                content = content,
                userId = userId,
            )
        }
    }
}