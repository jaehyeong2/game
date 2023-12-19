package jjfactory.game.item.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository :JpaRepository<Item, Long>{
}