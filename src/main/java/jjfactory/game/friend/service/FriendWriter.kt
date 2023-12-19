package jjfactory.game.friend.service

import jjfactory.game.achievement.domain.Friend

interface FriendWriter {
    fun write(friend: Friend): Friend
}