package jjfactory.game.achievement.domain

import org.springframework.data.jpa.repository.JpaRepository

interface AchievementRepository :JpaRepository<Friend, Long>{
}