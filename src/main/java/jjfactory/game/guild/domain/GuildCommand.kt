package jjfactory.game.guild.domain

class GuildCommand {
    data class Create(
        val name: String,
    ) {
        fun toEntity(userId: Long): Guild {
            return Guild(
                name = name,
                createUserId = userId
            )
        }
    }
}