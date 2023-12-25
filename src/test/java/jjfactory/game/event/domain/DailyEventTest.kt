package jjfactory.game.event.domain

import jjfactory.game.EntityFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DailyEventTest {

    @Test
    fun `오픈 메소드를 실행해야 이벤트가 시작된다`() {
        val entityFactory = EntityFactory()

        //given
        val event = entityFactory.createEvent()

        //expected
        assertThat(event.isOn).isFalse
        assertThat(event.startedAt).isNull()

        event.open()
        assertThat(event.isOn).isTrue
        assertThat(event.startedAt).isNotNull
    }
}