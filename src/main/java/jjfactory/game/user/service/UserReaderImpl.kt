package jjfactory.game.user.service

import jjfactory.game.user.domain.User
import jjfactory.game.user.domain.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserReaderImpl(
    private val userRepository: UserRepository
) : UserReader {
    override fun existById(id: Long): Boolean {
        return userRepository.existsById(id)
    }

    override fun findById(id: Long): User {
        return userRepository.findByIdOrNull(id) ?: throw NotFoundException()
    }
}