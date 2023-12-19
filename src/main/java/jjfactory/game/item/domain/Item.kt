package jjfactory.game.item.domain

import jakarta.persistence.*

@Entity
class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: Int = 0,
    @Enumerated(EnumType.STRING)
    val type: Type

    //획득 방법 필요
) {

    enum class Type {

    }
}