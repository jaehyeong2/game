package jjfactory.game.event.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DailyEventTest {

    @Test
    fun `오픈 메소드를 실행해야 이벤트가 시작된다`() {
        //given
        val event = DailyEvent(
            name = "5000원 주는 이벤트!",
            stock = 100,
            rewardMoney = 5000
        )

        //expected
        assertThat(event.isOn).isFalse
        assertThat(event.startedAt).isNull()

        event.open()
        assertThat(event.isOn).isTrue
        assertThat(event.startedAt).isNotNull
    }
}