package jjfactory.game.guild.service

import jjfactory.game.guild.domain.Guild

interface GuildWriter {
    fun write(guild: Guild): Guild
}