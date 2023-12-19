package jjfactory.game.friend.service

import jjfactory.game.achievement.domain.Friend

interface FriendReader {
    fun findByRequestUserIdAndReceiveUserId(requestUserId: Long, receiveUserId: Long): Friend
}