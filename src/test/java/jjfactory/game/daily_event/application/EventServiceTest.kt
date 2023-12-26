package jjfactory.game.daily_event.application

import jjfactory.game.EntityFactory
import jjfactory.game.daily_event.domain.DailyEventRepository
import jjfactory.game.daily_event.exception.AlreadyJoinedException
import jjfactory.game.daily_event.exception.SoldOutException
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.transaction.annotation.Transactional

@Import(EntityFactory::class)
@SpringBootTest
class EventServiceTest {
    @Autowired
    lateinit var entityFactory: EntityFactory

    @Autowired
    lateinit var eventRepository: DailyEventRepository

    @Autowired
    lateinit var eventService: EventService

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>

    @BeforeEach
    fun clear() {
        redisTemplate.execute {
            it.flushAll()
        }
    }

    @Transactional
    @Test
    fun `이벤트 오픈 시 on으로 바뀐다`() {
        //given
        val initEvent = entityFactory.createEvent()
        val event = eventRepository.save(initEvent)

        //when
        eventService.openEvent(event.id!!)

        //then
        assertThat(event.isOn).isTrue
    }

    @Transactional
    @Test
    fun `동일 이벤트 중복참여시 익셉션`() {
        //given
        val initEvent = entityFactory.createEvent()
        initEvent.open()

        val event = eventRepository.save(initEvent)

        //expected
        eventService.joinEvent(event.id!!, 2L)

        assertThatThrownBy {
            eventService.joinEvent(event.id!!, 2L)
        }.isInstanceOf(AlreadyJoinedException::class.java)
    }

    @Transactional
    @Test
    fun `레디스에 값 정상 추가`() {
        //given
        val initEvent = entityFactory.createEvent()
        initEvent.open()

        val event = eventRepository.save(initEvent)

        //when
        eventService.joinEvent(event.id!!, 2L)

        //then
        var key = "event-stock"
        assertThat(redisTemplate.opsForSet().isMember(key, "2")).isTrue
    }

    @Transactional
    @Test
    fun `재고 없으면 참여 시 exception`() {
        //given
        val initEvent = entityFactory.createEvent()
        initEvent.open()

        val event = eventRepository.save(initEvent)
        val stock = event.stock

        //expected
        assertThatThrownBy {
            for (i in 1 .. stock+1){
                eventService.joinEvent(event.id!!, i.toLong())
            }
        }.isInstanceOf(SoldOutException::class.java)
    }

    @Transactional
    @Test
    fun `재고 없으면 참여 시 exception2`() {
        //given
        val initEvent = entityFactory.createEvent(1000)
        initEvent.open()

        val event = eventRepository.save(initEvent)
        val stock = event.stock

        //expected
        assertThatThrownBy {
            for (i in 1 .. stock+1){
                eventService.joinEvent(event.id!!, i.toLong())
            }
        }.isInstanceOf(SoldOutException::class.java)
    }

}