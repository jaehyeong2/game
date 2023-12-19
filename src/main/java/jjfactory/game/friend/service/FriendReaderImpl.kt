package jjfactory.game.friend.service

import jjfactory.game.achievement.domain.Friend
import org.springframework.stereotype.Component

@Component
class FriendReaderImpl : FriendReader {
    override fun findByRequestUserIdAndReceiveUserId(requestUserId: Long, receiveUserId: Long): Friend {
        TODO("Not yet implemented")
    }
}