package jjfactory.game.guild.service

import jjfactory.game.guild.domain.Guild
import jjfactory.game.guild.domain.GuildRepository
import org.springframework.stereotype.Component

@Component
class GuildWriterImpl(
    private val guildRepository: GuildRepository
) : GuildWriter {
    override fun write(guild: Guild): Guild {
        return guildRepository.save(guild)
    }
}