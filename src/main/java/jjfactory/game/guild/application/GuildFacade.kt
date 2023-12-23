package jjfactory.game.guild.application

import jjfactory.game.guild.domain.GuildCommand
import jjfactory.game.guild.service.GuildReader
import jjfactory.game.guild.service.GuildWriter
import jjfactory.game.user.service.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class GuildFacade(
    private val guildReader: GuildReader,
    private val guildWriter: GuildWriter,
    private val userReader: UserReader
) {
    final val GUILD_CREATE_COST = 50000

    @Transactional
    fun createGuild(command: GuildCommand.Create, userId: Long): Long {
        val user = userReader.findById(userId)
        user.decreaseMoney(GUILD_CREATE_COST)

        if(guildReader.existByName(command.name)) throw RuntimeException("중복")

        val initGuild = command.toEntity(user.id!!)
        val guild = guildWriter.write(initGuild)

        return guild.id!!
    }
}