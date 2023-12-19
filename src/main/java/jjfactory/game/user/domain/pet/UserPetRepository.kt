package jjfactory.game.user.domain.pet

import org.springframework.data.jpa.repository.JpaRepository

interface UserPetRepository :JpaRepository<UserPet, Long>{
}