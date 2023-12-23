package jjfactory.game.guild.service

import jjfactory.game.guild.domain.Guild
import jjfactory.game.guild.domain.GuildRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class GuildReaderImpl(
    private val guildRepository: GuildRepository
) : GuildReader {
    override fun existByName(name: String): Boolean {
        return guildRepository.existsByName(name)
    }

    override fun findById(id: Long): Guild {
        return guildRepository.findByIdOrNull(id) ?: throw NotFoundException()
    }
}