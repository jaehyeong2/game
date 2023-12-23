package jjfactory.game.guild.application

import jjfactory.game.EntityFactory
import jjfactory.game.guild.domain.Guild
import jjfactory.game.guild.domain.GuildCommand
import jjfactory.game.guild.domain.GuildRepository
import jjfactory.game.user.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

@Import(EntityFactory::class)
@SpringBootTest
class GuildFacadeTest(
) {
    @Autowired
    lateinit var guildFacade: GuildFacade

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var guildRepository: GuildRepository

    @Autowired
    lateinit var entityFactory: EntityFactory

    @Transactional
    @Test
    fun `포인트 부족하면 생성 실패`() {
        //given
        val initGuild = GuildCommand.Create(
            name = "kk길드"
        )

        val initUser = entityFactory.createNoPointUser()
        val user = userRepository.save(initUser)

        //expected
        assertThatThrownBy {
            guildFacade.createGuild(initGuild, user.id!!)
        }.isInstanceOf(RuntimeException::class.java)

    }

    @Transactional
    @Test
    fun `이름 중복이면 실패`() {
        //given
        val guild = Guild(
            name = "kk길드",
            createUserId = 2L
        )

        guildRepository.save(guild)

        val initUser = entityFactory.createNoPointUser()
        val user = userRepository.save(initUser)


        val initGuild = GuildCommand.Create(
            name = "kk길드"
        )

        //expected
        assertThatThrownBy {
            guildFacade.createGuild(initGuild, user.id!!)
        }.isInstanceOf(RuntimeException::class.java)

    }

    @Transactional
    @Test
    fun `포인트 충본하면 성공`() {
        //given
        val initGuild = GuildCommand.Create(
            name = "kk길드"
        )

        val initUser = entityFactory.createNoPointUser()
        initUser.money = 50000
        val user = userRepository.save(initUser)

        //when
        val result = guildFacade.createGuild(initGuild, user.id!!)

        //then
        assertThat(result).isNotNull
        assertThat(user.money).isEqualTo(0)
    }
}