package jjfactory.game.event.application

import jjfactory.game.EntityFactory
import jjfactory.game.event.domain.DailyEventRepository
import jjfactory.game.event.exception.AlreadyJoinedException
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
    fun `동일 이벤트 중복참여는 불가능하다`() {
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

}