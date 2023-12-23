package jjfactory.game.guild.application

import jjfactory.game.guild.domain.Guild
import jjfactory.game.guild.domain.GuildCommand
import jjfactory.game.guild.domain.GuildRepository
import jjfactory.game.user.domain.User
import jjfactory.game.user.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class GuildFacadeTest {
    @Autowired
    lateinit var guildFacade: GuildFacade

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var guildRepository: GuildRepository

    @Transactional
    @Test
    fun `포인트 부족하면 생성 실패`() {
        val initGuild = GuildCommand.Create(
            name = "kk길드"
        )

        val initUser = User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100,
            money = 1000
        )

        val user = userRepository.save(initUser)

        assertThatThrownBy {
            guildFacade.createGuild(initGuild, user.id!!)
        }.isInstanceOf(RuntimeException::class.java)

    }

    @Transactional
    @Test
    fun `이름 중복이면 실패`() {
        val guild = Guild(
            name = "kk길드",
            createUserId = 2L
        )

        guildRepository.save(guild)

        val initGuild = GuildCommand.Create(
            name = "kk길드"
        )

        val initUser = User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100,
            money = 50000
        )

        val user = userRepository.save(initUser)

        assertThatThrownBy {
            guildFacade.createGuild(initGuild, user.id!!)
        }.isInstanceOf(RuntimeException::class.java)

    }

    @Transactional
    @Test
    fun `포인트 충본하면 성공`() {
        val initGuild = GuildCommand.Create(
            name = "kk길드"
        )

        val initUser = User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100,
            money = 50000
        )

        val user = userRepository.save(initUser)

        val result = guildFacade.createGuild(initGuild, user.id!!)

        assertThat(result).isNotNull
        assertThat(user.money).isEqualTo(0)
    }
}