package jjfactory.game.user.domain.achievement

import org.springframework.data.jpa.repository.JpaRepository

interface UserAchievementRepository :JpaRepository<UserAchievement, Long>{
}