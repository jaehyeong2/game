package jjfactory.game.stage.application

import jjfactory.game.EntityFactory
import jjfactory.game.stage.domain.ChapterRepository
import jjfactory.game.stage.domain.StageRepository
import jjfactory.game.stage.domain.clear.StageClearCommand
import jjfactory.game.user.domain.UserRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.transaction.annotation.Transactional

@Import(EntityFactory::class)
@SpringBootTest
class StageClearFacadeTest {
    @Autowired
    lateinit var stageClearFacade: StageClearFacade
    @Autowired
    lateinit var stageRepository: StageRepository
    @Autowired
    lateinit var chapterRepository: ChapterRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var entityFactory: EntityFactory

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>

    @BeforeEach
    fun init(){
        redisTemplate.execute { it.flushAll() }
    }


    @Transactional
    @Test
    fun clear() {
        //given
        val initChapter = entityFactory.createChapter()
        val chapter = chapterRepository.save(initChapter)

        val initStage = entityFactory.createStage(chapter, ordering = 9)
        val stage = stageRepository.save(initStage)
        val initStage2 = entityFactory.createStage(chapter, ordering = 10)
        val stage2 = stageRepository.save(initStage2)

        val initUser = entityFactory.createUser(stageClearLevel = 8)
        val user = userRepository.save(initUser)
        val initUser2 = entityFactory.createUser(stageClearLevel = 8)
        val user2 = userRepository.save(initUser2)

        val command = StageClearCommand.Create(
            playTime = 60000
        )

        //when
        val result = stageClearFacade.clear(stage.id!!, command, user.id!!)
        stageClearFacade.clear(stage.id!!, command, user2.id!!)
        stageClearFacade.clear(stage2.id!!, command, user2.id!!)

        val rankKey = "daily-active-rank"


        //then
        assertThat(result).isNotNull

        redisTemplate.opsForZSet().reverseRangeWithScores(rankKey, 0, 1)
            ?.map { it.score }!!.toList()[0]?.let { assertThat(it).isEqualTo(600.0) }

        assertThat(user.clearStageLevel).isEqualTo(9)
        assertThat(user2.clearStageLevel).isEqualTo(10)
    }
}