package jjfactory.game.user.domain

import org.assertj.core.api.Assertions.assertThat
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
}