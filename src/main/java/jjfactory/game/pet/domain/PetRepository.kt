package jjfactory.game.pet.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PetRepository :JpaRepository<Pet, Long>{
}