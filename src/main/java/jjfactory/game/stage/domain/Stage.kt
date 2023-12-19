package jjfactory.game.stage.domain

import jakarta.persistence.*

@Entity
class Stage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val chapter: Chapter,
    val ordering: Int,
    val name: String,
) {
}