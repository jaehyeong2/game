package jjfactory.game.user.domain

import jjfactory.game.EntityFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserTest{
    private lateinit var user: User

    @BeforeEach
    fun init(){
        val entityFactory = EntityFactory()
        user = entityFactory.createNoPointUser()
        user.money = 1000
    }


    @Test
    fun `연속 호출되어도 레벨은 한번만 오른다`() {
        //when
        val level = user.level

        for (i in 1..500) {
            user.levelUp()
        }


        //then
        assertThat(user.level).isEqualTo(level+1)
    }

    @Test
    fun `포인트 정상 차감`() {
        //when
        user.decreaseMoney(500)

        //then
        assertThat(user.money).isEqualTo(500)
    }

    @Test
    fun `포인트 보다 많이 깎이면 예외`() {
        //expected
        assertThatThrownBy {
            user.decreaseMoney(10000)
        }.isInstanceOf(RuntimeException::class.java)

    }
}