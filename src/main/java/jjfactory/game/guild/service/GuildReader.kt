package jjfactory.game.guild.service

import jjfactory.game.guild.domain.Guild

interface GuildReader {
    fun existByName(name: String): Boolean
    fun findById(id: Long): Guild
}