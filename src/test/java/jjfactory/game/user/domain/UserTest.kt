package jjfactory.game.user.domain

import jjfactory.game.EntityFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserTest {
    private lateinit var user: User

    @BeforeEach
    fun init() {
        val entityFactory = EntityFactory()
        user = entityFactory.createNoPointUser()
        user.money = 1000
    }


    @Test
    fun `경험치가 100이 넘으면 레벨이 오르고 초과한 경험치만큼만 경험치가 남는다`() {
        //when
        val level = user.level
        user.exp = 99


        user.increaseExp(3)

        //then
        assertThat(user.level).isEqualTo(level + 1)
        assertThat(user.exp).isEqualTo(2)
    }

    @Test
    fun `0~음수 경험치는 반영 되지 않는다`() {
        //given

        //expected
        assertThatThrownBy {
            user.increaseExp(-100)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `머니 정상 차감`() {
        //when
        user.decreaseMoney(500)

        //then
        assertThat(user.money).isEqualTo(500)
    }

    @Test
    fun `보유 머니 보다 많이 깎이면 예외`() {
        //expected
        assertThatThrownBy {
            user.decreaseMoney(10000)
        }.isInstanceOf(RuntimeException::class.java)

    }
}