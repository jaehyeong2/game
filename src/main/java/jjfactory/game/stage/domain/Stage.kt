package jjfactory.game.stage.domain

import jakarta.persistence.*

@Entity
class Stage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val chapter: Chapter,
    var clearPoint: Int,
    var exp: Int,
    val ordering: Int,
    val name: String,
) {
}