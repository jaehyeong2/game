package jjfactory.game.chat.infra

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import jjfactory.game.chat.domain.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageRepository : JpaRepository<ChatMessage, Long>, KotlinJdslJpqlExecutor {
}