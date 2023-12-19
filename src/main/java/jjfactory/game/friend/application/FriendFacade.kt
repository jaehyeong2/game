package jjfactory.game.friend.application

import jjfactory.game.achievement.domain.Friend
import jjfactory.game.friend.service.FriendReader
import jjfactory.game.friend.service.FriendWriter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FriendFacade(
    private val friendWriter: FriendWriter,
    private val friendReader: FriendReader
) {
    @Transactional
    fun request(requestUserId: Long, receiveUserId: Long): Long {
        val initFriend = Friend(
            requestUserId = requestUserId,
            receiveUserId = receiveUserId,
        )

        return friendWriter.write(initFriend).id!!
    }

    @Transactional
    fun accept(requestUserId: Long, receiveUserId: Long): Long {
        val friend = friendReader.findByRequestUserIdAndReceiveUserId(requestUserId, receiveUserId)
        friend.accept()

        return friend.id!!
    }
}