package jjfactory.game.daily_event.application

import jjfactory.game.EntityFactory
import jjfactory.game.daily_event.domain.DailyEvent
import jjfactory.game.daily_event.domain.DailyEventRepository
import jjfactory.game.daily_event.domain.log.DailyEventLogRepository
import jjfactory.game.daily_event.exception.AlreadyJoinedException
import jjfactory.game.daily_event.exception.SoldOutException
import jjfactory.game.user.domain.UserRepository
import jjfactory.game.user.domain.support.UserEventListener
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@Import(EntityFactory::class)
@SpringBootTest
class EventDbServiceTest {
    @Autowired
    lateinit var entityFactory: EntityFactory

    @Autowired
    lateinit var eventRepository: DailyEventRepository

    @Autowired
    lateinit var eventLogRepository: DailyEventLogRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var eventDbService: EventDbService

    @MockBean
    lateinit var userEventListener: UserEventListener

    lateinit var event: DailyEvent

    @BeforeEach
    fun init() {
        val initEvent = entityFactory.createEvent(stock = 10)
        initEvent.open()

        event = eventRepository.save(initEvent)
    }

    @Transactional
    @Test
    fun `동일 이벤트 중복참여시 익셉션`() {
        //given
        val initUser = entityFactory.createUser()
        val user = userRepository.save(initUser)

        //expected
        eventDbService.joinEvent(event.id!!, user.id!!)

        assertThatThrownBy {
            eventDbService.joinEvent(event.id!!, user.id!!)
        }.isInstanceOf(AlreadyJoinedException::class.java)
    }

    @Transactional
    @Test
    fun `이벤트 참여 성공 시 user의 money가 오르고 로그가 저장된다`() {
        //given
        val initUser = entityFactory.createUser(money = 0)
        val user = userRepository.save(initUser)

        //when
        eventDbService.joinEvent(event.id!!, user.id!!)

        //then
        assertThat(user.money).isEqualTo(event.rewardMoney.toLong())
        assertThat(eventLogRepository.findAll()).isNotEmpty
    }

    @Test
    fun `동시성 이슈가 없을 경우 재고만큼만 저장되고 익셉션`() {
        //given
        val initUser = entityFactory.createUser()
        val user = userRepository.save(initUser)

        val cnt = event.stock + 20

        //expected
        assertThatThrownBy {
            for (i in 1..cnt) {
                eventDbService.joinEvent(event.id!!, i.toLong())
            }
        }.isInstanceOf(SoldOutException::class.java)

        assertThat(eventLogRepository.findAll().size).isEqualTo(event.stock)
    }

    @Test
    fun `동시성 이슈 발생 시 재고보다 더 많은 인원이 이벤트 참여가 가능`() {
        //given
        val threadCnt = event.stock + 20
        val executorService = Executors.newFixedThreadPool(threadCnt)
        val countDownLatch = CountDownLatch(threadCnt)

        //stub
        doNothing().whenever(userEventListener).dailyEventJoinedEventListener(any())

        //when
        for (i in 1..threadCnt) {
            executorService.execute {
                eventDbService.joinEvent(event.id!!, i.toLong())
                countDownLatch.countDown()
            }
        }

        countDownLatch.await()

        //then
        assertThat(eventLogRepository.findAll().size).isNotEqualTo(event.stock)
    }
}