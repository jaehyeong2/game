package jjfactory.game.user.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun `연속 호출되어도 레벨은 한번만 오른다`() {
        val user = User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100
        )

        for (i in 1..500) {
            user.levelUp()
        }

        user.levelUp()

        assertThat(user.level).isEqualTo(3)
    }

    @Test
    fun `포인트 정상 차감`() {
        val user = User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100,
            point = 1000
        )

        user.decreasePoint(500)

        assertThat(user.point).isEqualTo(500)
    }

    @Test
    fun `포인트 보다 많이 깎이면 예외`() {
        val user = User(
            username = "kim",
            password = "123",
            level = 2,
            exp = 100,
            point = 1000
        )

        assertThatThrownBy {
            user.decreasePoint(10000)
        }.isInstanceOf(RuntimeException::class.java)

    }
}