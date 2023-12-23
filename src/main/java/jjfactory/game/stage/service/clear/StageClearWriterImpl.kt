package jjfactory.game.stage.service.clear

import jjfactory.game.user.domain.stage.StageClear
import jjfactory.game.user.domain.stage.StageClearRepository
import org.springframework.stereotype.Component

@Component
class StageClearWriterImpl(
    private val stageClearRepository: StageClearRepository
) : StageClearWriter {
    override fun write(stageClear: StageClear): StageClear {
        return stageClearRepository.save(stageClear)
    }
}