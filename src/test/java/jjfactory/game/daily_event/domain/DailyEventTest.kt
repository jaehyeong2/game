package jjfactory.game.daily_event.domain

import jjfactory.game.EntityFactory
import jjfactory.game.daily_event.exception.SoldOutException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
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

    @Test
    fun `재고 감소 성공`() {
        val entityFactory = EntityFactory()
        //given
        val event = entityFactory.createEvent()
        val stock = event.stock

        //when
        event.decreaseStock()
        //then
        assertThat(event.stock).isEqualTo(stock-1)
    }

    @Test
    fun `재고 없으면 감소 실패`() {
        val entityFactory = EntityFactory()

        //given
        val event = entityFactory.createEvent(stock = 0)

        //expected
        assertThatThrownBy{
            event.decreaseStock()
        }.isInstanceOf(SoldOutException::class.java)
    }
}