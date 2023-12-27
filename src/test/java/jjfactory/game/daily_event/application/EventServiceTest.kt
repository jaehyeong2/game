package jjfactory.game.daily_event.application

import jjfactory.game.EntityFactory
import jjfactory.game.daily_event.domain.DailyEvent
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
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

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
    lateinit var event: DailyEvent

    var key = "event-stock"

    @BeforeEach
    fun init() {
        val initEvent = entityFactory.createEvent(stock = 10)
        initEvent.open()

        event = eventRepository.save(initEvent)

        redisTemplate.delete(key)
    }

    @Test
    fun `동일 이벤트 중복참여시 익셉션`() {
        //given

        //expected
        eventService.joinEvent(event.id!!, 2L)

        assertThatThrownBy {
            eventService.joinEvent(event.id!!, 2L)
        }.isInstanceOf(AlreadyJoinedException::class.java)
    }

    @Test
    fun `이벤트 참여 시 레디스에 저장된다`() {
        //given

        //when
        eventService.joinEvent(event.id!!, 2L)
        //then
        assertThat(redisTemplate.opsForSet().isMember(key, "2")).isTrue
    }

    @Test
    fun `재고가 다 소모 되었으면 참여 시 exception이 발생한다`() {
        //given
        val stock = event.stock

        //expected
        assertThatThrownBy {
            for (i in 1..stock + 1) {
                eventService.joinEvent(event.id!!, i.toLong())
            }
        }.isInstanceOf(SoldOutException::class.java)
    }

    @Test
    fun `멀티스레드에서도 재고만큼만 레디스에 저장된다`() {
        //given
        val threadCnt = event.stock + 10
        val executorService = Executors.newFixedThreadPool(threadCnt)
        val countDownLatch = CountDownLatch(threadCnt)

        //when
        for (i in 1..threadCnt) {
            executorService.execute {
                try{
                    eventService.joinEvent(event.id!!, i.toLong())
                }finally {
                    countDownLatch.countDown()
                }
            }
        }

        countDownLatch.await()

        //then
        assertThat(redisTemplate.opsForSet().size(key)).isEqualTo(event.stock.toLong())
    }
}