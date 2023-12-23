package jjfactory.game.guild.domain

import org.springframework.data.jpa.repository.JpaRepository

interface GuildRepository : JpaRepository<Guild, Long> {
}